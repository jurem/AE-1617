using System;
using System.Diagnostics;
using System.Numerics;

namespace ConsoleApplication1 {
    class Program {        static BigInteger[] memo;        static void Main(string[] args) {
            Stopwatch sw = new Stopwatch();            double ticks; // running time in processor ticks
            long frequency = Stopwatch.Frequency; // processor frequency

            int step = 20;
            for (int i = 1; i < 7000; i += step) {                int repetitions = 100; // calculate every Fibonacci number many times (to calculate time more correct)
                sw.Restart(); // start measuring time                for (int j = 0; j < repetitions; j++) {
                    // Iterative
                    BigInteger foo = fibIter(i); // calculate n-th Fibonacci number

                    // Recursive
                    //initializeArray(i); // initialize/clear memo
                    //BigInteger foo = fibRek(i); // calculate n-th Fibonacci number
                }                sw.Stop();                ticks = sw.ElapsedTicks;                double averageTime = 1000 * ticks / (Stopwatch.Frequency); // ms                Console.WriteLine(averageTime / repetitions);            }
            Console.WriteLine("Done.");
            Console.ReadLine();        }
        static void initializeArray(int n) {            memo = new BigInteger[++n];            for (int i = 0; i < n; i++) {                memo[i] = -1; // default value            }
            // prepare first two values to avoid multiple if clauses in further calculation
            memo[0] = 0;            memo[1] = 1;        }        static BigInteger fibRek(int n) {            // recursive algorithm for calculating n-th Fibonacci number            if (memo[n - 1] != -1) {                // is already calculated                return memo[n - 1];            } else {                // not calculated yet                BigInteger f = fibRek(n - 1) + fibRek(n - 2);                memo[n - 1] = f; // save value                return f;            }        }
        static BigInteger fibIter(int n) {
            // iterative algorithm for calculating n-th Fibonacci number
            BigInteger a = 0;
            BigInteger b = 1;

            for (int i = 0; i < n - 1; i++) {
                BigInteger c = a + b;
                a = b;
                b = c;
            }

            return a;
        }    }}
