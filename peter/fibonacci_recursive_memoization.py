from time import time
from sys import getsizeof, setrecursionlimit

setrecursionlimit(10000)


def fib(n):
    if memo[n - 1] != -1:
        return memo[n - 1]
    else:
        f = fib(n - 1) + fib(n - 2)
        memo[n - 1] = f
        return f


for i in range(1, 7000, 20):
    repetitions = 100

    t = time()
    for _ in range(repetitions):
        memo = [-1 for _ in range(i + 1)]
        memo[0], memo[1] = 0, 1

        foo = fib(i)
    avg_time = (time() - t) / repetitions

    print avg_time * 1000  # ms
