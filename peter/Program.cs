﻿using System;
using System.Diagnostics;
using System.Numerics;

namespace ConsoleApplication1 {
    class Program {
            Stopwatch sw = new Stopwatch();
            long frequency = Stopwatch.Frequency; // processor frequency

            int step = 20;
            for (int i = 1; i < 7000; i += step) {
                sw.Restart(); // start measuring time
                    // Iterative
                    BigInteger foo = fibIter(i); // calculate n-th Fibonacci number

                    // Recursive
                    //initializeArray(i); // initialize/clear memo
                    //BigInteger foo = fibRek(i); // calculate n-th Fibonacci number
                }
            Console.WriteLine("Done.");
            Console.ReadLine();
        static void initializeArray(int n) {
            // prepare first two values to avoid multiple if clauses in further calculation
            memo[0] = 0;
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
        }