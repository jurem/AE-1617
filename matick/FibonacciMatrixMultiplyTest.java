import java.math.BigInteger;

public class FibonacciMatrixMultiplyTest {

	private static BigInteger[][] M2 = {{new BigInteger("1"), new BigInteger("1")}, {new BigInteger("1"), new BigInteger("0")}};
	private static BigInteger[][] A2 = {{new BigInteger("1"), new BigInteger("1")}, {new BigInteger("1"), new BigInteger("0")}};
	
	public static void main(String[] args) {
		
		int[] numbers = {10_000, 20_000, 50_000, 100_000, 200_000, 500_000};
		int n = 10_000;						// TODO modify for different Fibonacci numbers
		
		/*
		// Matrix Multiplication
		long start = System.nanoTime();
		BigInteger fib1 = fibMat1(n);
		long finish = System.nanoTime();
		double taken1 = (finish - start) / 1_000_000.0;
		System.out.println(n + "-th Fibonacci number: " + fib1.toString());
		System.out.println("-> time taken: " + taken1 + " ms");
		
		// Matrix Recursive 
		System.out.println("Power Matrix results: fibMat2Power - recursive");
		start = System.nanoTime();
		BigInteger fib2 = fibMat2Power(n);
		finish = System.nanoTime();
		double taken2 = (finish - start) / 1_000_000.0;
		System.out.println(n + "-th Fibonacci number: " + fib2.toString());
		System.out.println("-> time taken: " + taken2 + " ms");
		*/
		
		measureTimes();
	}
	
	
	public static void measureTimes() {
		int[] numbers = {10_000, 20_000, 50_000, 100_000, 200_000, 500_000};
		//int[] numbers = {5, 10, 15, 20};
		
		for (int n : numbers) {
			System.out.println(n + "-th Fibonacci number calculations");
			
			// Matrix Multiplication
			long start = System.nanoTime();
			BigInteger fib1 = fibMat1(n);
			long finish = System.nanoTime();
			double taken1 = (finish - start) / 1_000_000.0;
			System.out.println("Matrix Multiplication");
//			System.out.println(n + "-th Fibonacci number: " + fib1.toString());
			System.out.println("-> time taken: " + taken1 + " ms");
			
			// Matrix Power Recursive
			
			System.out.println("Matrix Power: fibMat2Power - recursive");
			start = System.nanoTime();
			BigInteger fib2 = fibMat2Power(n);
			finish = System.nanoTime();
			double taken2 = (finish - start) / 1_000_000.0;
//			System.out.println(n + "-th Fibonacci number: " + fib2.toString());
			System.out.println("-> time taken: " + taken2 + " ms");
			
			// Matrix Power Iterative - error
//			System.out.println("Matrix Power: fibMat3PowerIter - iterative");
//			start = System.nanoTime();
//			BigInteger fib3 = fibMat3PowerIter(n);
//			finish = System.nanoTime();
//			double taken3 = (finish - start) / 1_000_000.0;
//			System.out.println(n + "-th Fibonacci number: " + fib3.toString());
//			System.out.println("-> time taken: " + taken3 + " ms");
			
			// Simple iterative version
			start = System.nanoTime();
			BigInteger fib4 = fibonacciIterat(n);
			finish = System.nanoTime();
			double taken4 = (finish - start) / 1_000_000.0;
			System.out.println("Simple Iterative");
//			System.out.println(n + "-th Fibonacci number: " + fib4.toString());
			System.out.println("-> time taken: " + taken4 + " ms");
			
		}
	}
	
	public static BigInteger fibMat1(int n) {
		
		BigInteger[][] mult1 = {{new BigInteger("1"), new BigInteger("1")}, {new BigInteger("1"), new BigInteger("0")}};
		BigInteger[][] M = {{new BigInteger("1"), new BigInteger("0")}, {new BigInteger("0"), new BigInteger("1")}};	// starting matrix
		
		for (int i = 0; i < (n-1); i++) {
			// multiply matrix
			//M = multiply2x2Mat(M, mult1);
			multiply2x2MatInternal(M, mult1);
		}
		
		return M[0][0];
	}
	
	public static BigInteger[][] multiply2x2Mat(BigInteger[][] m, BigInteger[][] n) {
		BigInteger[][] res = new BigInteger[2][2];
		BigInteger a = m[0][0].multiply(n[0][0]).add(m[0][1].multiply(n[1][0]));
		BigInteger b = m[0][0].multiply(n[0][1]).add(m[0][1].multiply(n[1][1]));
		BigInteger c = m[1][0].multiply(n[0][0]).add(m[1][1].multiply(n[1][0]));
		BigInteger d = m[1][0].multiply(n[0][1]).add(m[1][1].multiply(n[1][1]));

        res[0][0] = a;
        res[0][1] = b;
        res[1][0] = c;
        res[1][1] = d;
        
        return res;
	}
	
	public static void multiply2x2MatInternal(BigInteger[][] m, BigInteger[][] n) {
		BigInteger a = (m[0][0].multiply(n[0][0])).add(m[0][1].multiply(n[1][0]));
		BigInteger b = (m[0][0].multiply(n[0][1])).add(m[0][1].multiply(n[1][1]));
		BigInteger c = (m[1][0].multiply(n[0][0])).add(m[1][1].multiply(n[1][0]));
		BigInteger d = (m[1][0].multiply(n[0][1])).add(m[1][1].multiply(n[1][1]));

		m[0][0] = a;
		m[0][1] = b;
		m[1][0] = c;
		m[1][1] = d;
	}
	
	
	
	// calling recursive - uses M2 and A2
	public static BigInteger fibMat2Power(int n) {
		// starting matrix
		//BigInteger[][] M = {{new BigInteger("1"), new BigInteger("0")}, {new BigInteger("0"), new BigInteger("1")}};
		M2[0][0] = new BigInteger("1");
		M2[0][1] = new BigInteger("1");
		M2[1][0] = new BigInteger("1");
		M2[1][1] = new BigInteger("0");
		
		matrixPow(n);
		return M2[1][0];
	}
	
	public static void matrixPow(int num) {
		
		if (num <= 1) return;
		
		if (num > 1) {
			matrixPow(num / 2);
			multiply2x2MatInternal(M2, M2);
		} else return;
		
		if (num % 2 != 0) {
			//BigInteger[][] matA = {{new BigInteger("1"), new BigInteger("1")}, {new BigInteger("1"), new BigInteger("0")}};
			multiply2x2MatInternal(M2, A2);
		}
	}
	
	// iterative power matrix solution
	public static BigInteger fibMat3PowerIter(int num) {
		int n = num;
		if (n <= 1) {
			return new BigInteger(String.valueOf(n));
		}
		
		// starting matrix
		BigInteger[][] M = {{new BigInteger("1"), new BigInteger("0")}, {new BigInteger("0"), new BigInteger("1")}};
		BigInteger[][] A = {{new BigInteger("1"), new BigInteger("1")}, {new BigInteger("1"), new BigInteger("0")}};
		
		while (n > 0) {
			if (n%2 == 1) {
				multiply2x2MatInternal(M, A);
			}
			n = n / 2;
			multiply2x2MatInternal(M, M);
		}
		return M[0][0];
	}
	
	
	public static BigInteger[][] powerOf2x2Mat(BigInteger[][] m, int pow) {
		BigInteger[][] res = m;
		for (int i = 0; i < pow; i++) {
			res = multiply2x2Mat(res, m);
		}
		return res;
	}
	
	/**
	 * returns the Nth number in the Fibonacci sequence
	 */
	public static BigInteger fibonacciIterat(int n) {
		BigInteger lo = new BigInteger("0");
		BigInteger hi = new BigInteger("1");
		for (int i = 0; i < n; i++) {
			hi = lo.add(hi);
			lo = hi.subtract(lo);
		}
		return lo;
	}

}
