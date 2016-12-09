import java.math.BigInteger;
import java.util.ArrayList;

public class Fibonacci {

    int previous = 0;

    public static void main (String[] args) {

        /*
        * Povecuje fibonaccija, dokler ne zmanjka pomnilnika
        * izpise zadnjega, ki se ga je dalo izracunati
        * z args[0] se lahko specificira, kje zacne
        * */

        int step = 10000;
        int counter = 10;
	    int retries = 0;

        if (args.length >= 1) {
            counter = Integer.parseInt(args[0]);
        }
        while (true) {
            System.gc();
            Fibonacci f = new Fibonacci(counter);
            try {
                BigInteger result = f.runRekMem();
            } catch (OutOfMemoryError oome) {
                if (step == 1) {
			if (retries < 5){
				// try the last one 5 times to make sure it is correct
				retries++;
				counter--;
			} else {
				System.out.println(counter - 1);
				return;
			}
                } else {
                    // zadnji ki je sel cez
                    counter = counter - step;
                    step /= 10;
                }
            } catch (StackOverflowError soe) {
                System.err.println("Stack overflow, counter = " + counter);
                System.err.println("Increase the stack");
                return;
            }
            counter += step;
        }
    }

    private BigInteger[] mem;

    private long time;
    private int n;


    public Fibonacci (int n) {

        this.time = 0;
        this.n = n;
    }

    public BigInteger runRekMem() {
        this.mem = new BigInteger[n];

        long startTime = System.nanoTime();
        BigInteger b = fib_rek_mem(this.n - 1);
        long stopTime = System.nanoTime();
        this.mem = null; // clear the ram
        System.gc();
        this.time = stopTime - startTime;
        return b;
    }

    private  BigInteger fib_rek_mem(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            if (mem[n] == null) {
                BigInteger f = fib_rek_mem(n-1).add(fib_rek_mem(n-2));
                mem[n] = f;
                return f;
            } else {
                return mem[n];
            }
        }
    }

    public long getTime() {
        return time;
    }
}
