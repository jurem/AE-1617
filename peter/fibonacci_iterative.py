from time import time


def fib(n):
    a = 0
    b = 0
    for _ in range(n):
        c = a + b
        a = b
        b = c
    return a


for i in range(1, 7000, 20):
    repetitions = 100

    t = time()
    for _ in range(repetitions):
        foo = fib(i)
    avg_time = (time() - t) / repetitions
    print(avg_time * 1000)  # [ms]
