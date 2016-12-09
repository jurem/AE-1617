import java.lang.instrument.Instrumentation;
import java.math.BigInteger;

public class Fibonacci2 {

    public static void main (String[] args) {
        /*
        * Memoizacija brez ciscenja pomnilnika
        * merjenje velikosti tabele
        * prvi for loop meri za majhne velikosti (pod 5MB)
        * drugi za velike velikosti (do 10GB)
        * */

        int startSmall = 100;
        int stopSmall = 10000;
        int stepSmall = 100;

        int startBig = 20000;
        int stepBig = 20000;
        int stopBig = 460000;

        if (args.length >= 6) {
            startSmall = Integer.parseInt(args[0]);
            stepSmall = Integer.parseInt(args[1]);
            stopSmall = Integer.parseInt(args[2]);

            startBig = Integer.parseInt(args[3]);
            stepBig = Integer.parseInt(args[4]);
            stopBig = Integer.parseInt(args[5]);
        }

        System.out.printf("%7s,  %11s,   %8s,  %7s,  %10s\n", "fib", "array B",  "array MB", "largest B", " largest kB");

        for (int i = startSmall; i <= stopSmall;  i += stepSmall) {
            Fibonacci2 f = new Fibonacci2(i);
            BigInteger result = f.runRekMem();
            long size = getMemSize(f);
            long largest = getSize(f.mem[i-1]);
            System.out.printf("%7d,  %11d,  %4.2f MB,  %11d,  %4.2f kB\n", i, size, (double)(size)/1000000.0, largest, (double)(largest)/1000.0);
            f = null;
            System.gc();
        }

        System.out.println();

        for (int i = startBig; i <= stopBig;  i += stepBig) {
           Fibonacci2 f = new Fibonacci2(i);
           BigInteger result = f.runRekMem();
           long size = getMemSize(f);
           long largest = getSize(f.mem[i-1]);
           System.out.printf("%7d,  %11d,  %4.2f MB,  %11d,  %4.2f kB\n", i, size, (double)(size)/1000000.0, largest, (double)(largest)/1000.0);
           f = null;
           System.gc();
        }
    }



    private BigInteger[] mem;
    private long time;
    private int n;


    public Fibonacci2(int n) {
        this.time = 0;
        this.n = n;
        this.mem = new BigInteger[n];
        this.mem[0] = BigInteger.ZERO;
        this.mem[1] = BigInteger.ONE;
    }

    public BigInteger runRekMem() {
        long startTime = System.nanoTime();
        BigInteger b = fib_rek_mem(this.n - 1);
        long stopTime = System.nanoTime();
        this.time = stopTime - startTime;
        return b;
    }

    private  BigInteger fib_rek_mem(int n) {
        if (mem[n] == null) {
            BigInteger f = fib_rek_mem(n-1).add(fib_rek_mem(n-2));
            mem[n] = f;
            return f;
        } else {
            return mem[n];
        }
    }

    public long getTime() {
        return time;
    }

    public static long getMemSize(Fibonacci2 f) {
        long sum = 0;
        for (int i = 0; i<f.mem.length; i++) {
            sum += getSize(f.mem[i]);
        }
        return sum;
    }

    public static long getSize(BigInteger bi) {
        return 40 + bi.bitLength()/8;
    }

}
