from time import time


def fib(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a


for i in range(1, 5000, 20):
    repetitions = 100

    t = time()
    for _ in range(repetitions):
        foo = fib(i)
    avg_time = (time() - t) / repetitions
    print(avg_time * 1000)  # [ms]
