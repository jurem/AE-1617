import math

def fibonaci5_4(n):
    if n>1:
        return fibonaci5_4(n-1)+fibonaci5_4(n-2)
    else:
        if n==0:
            return 0
        else:
            return 1

def fibonaci5_5(n):
    sez = [-1 for _ in range(n+1)]
    sez[0] = 0
    sez[1] = 1

    def pomozniFib(m):
        if sez[m]<0:
            sez[m] = pomozniFib(m-1) + pomozniFib(m-2)
            return sez[m]
        else:
            return sez[m]

    return pomozniFib(n)

def fibonaci5_6(n):
    sez = [-1 for _ in range(n+1)]
    sez[0] = 0
    sez[1] = 1

    for i in range(2,n+1):
        sez[i] = sez[i-1] + sez[i-2]

    return sez[n]

def fibonaci5_7(n):
    a = 0
    b = 1

    for _ in range(n-1):
        (a, b) = (b, a+b)

    return b

def fibonaci5_7klasicni(n):
    a = 0
    b = 1

    for _ in range(n-1):
        c = a+b
        a = b
        b = c

    return b

def fibonaci5_8(n):
    fi = (1 + 5**(1/2))/2
    kor5 = 5**(1/2)
    return round((fi**n)/kor5)

def fibonaci5_9(n):
    fi = (1 + 5**(1/2))/2
    psi = 1- fi
    kor5 = 5**(1/2)
    return round((fi**n - psi**n)/kor5)

def fibonaci5_10(n):
    (c1, c2, c3, c4) = (1, 1, 1, 0)
    (b1, b2, b3, b4) = (1, 1, 1, 0)
    
    for _ in range(n-1):
        (b1, b2, b3, b4) = (b1 * c1 + b2 * c3, b1 * c2 + b2 * c4,
                            b3 * c1 + b4 * c3, b3 * c2 + b4 * c4)

    return b3

def fibonaci5_11(n):
    x = bin(n)
    x = x[x.index("1"):]
    konec = len(x)-1
    sezMatrik = [False for _ in range(konec)]
    ##print(x)
    
    a1 = 1
    a2 = 1
    a3 = 1
    a4 = 0

    for i in range(konec):
        (a1, a2,
         a3, a4)=(a1 * a1 + a2 * a3, a1 * a2 + a2 * a4,
                  a3 * a1 + a4 * a3, a3 * a2 + a4 * a4)

        sezMatrik[i] = (a1, a2, a3, a4)

    sezMatrik.reverse()
    (b1, b2, b3, b4) = (1, 0, 0, 1)
    for i in range(konec):
        if x[i] == "1":
            (c1, c2, c3, c4) = sezMatrik[i]
            (b1, b2, b3, b4) = (b1 * c1 + b2 * c3, b1 * c2 + b2 * c4,
                                b3 * c1 + b4 * c3, b3 * c2 + b4 * c4)

    return b3

import time

def test():
    ##sezTest = [10**i for i in range(4)]
    sezTest = [10, 100, 200, 500]
    sezAlgIme = [##("fibonaci5_4",fibonaci5_4),
                 ("fibonaci5_5",fibonaci5_5),
                 ("fibonaci5_6",fibonaci5_6),
                 ("fibonaci5_7",fibonaci5_7),
                 ("fibonaci5_8",fibonaci5_8),
                 ("fibonaci5_9",fibonaci5_9),
                 ("fibonaci5_10",fibonaci5_10),
                 ("fibonaci5_11",fibonaci5_11)]
    ##sloAlg = dict(sezAlgIme)
    matRez = []
    for n in sezTest:
        print()
        print("########################################")
        print("n =",n)
        print()
        sezRezultatov = list()
        sezRezultatov2 = list()
        ##for ime in sloAlg:
            ##algoritemFib = sloAlg[ime]
        for (ime, algoritemFib) in sezAlgIme:
            print("Algoritem:", ime)
            start = time.time()
            rez = algoritemFib(n)
            end = time.time()
            cas = end - start
            print("Cas:", cas)
            sezRezultatov.append((ime+
                                  ", rez: "+str(rez)+
                                  ", cas: "+str(cas)))

            ##print(sezRezultatov)

            sezRezultatov2.append((ime,rez,cas))
            matRez.append(sezRezultatov2)

    return sezRezultatov2
            
        

if __name__ == "__main__":
    test()
    print()
    print("""dodatniPrimeri()""")


def dodatniPrimeri():
    print("""x=fibonaci5_7(100000)""")
    print("""x=fibonaci5_8(1474)""")
    print("""x=fibonaci5_11(100000)""")
    print("""superTest()""")
    
def superTest():
    n = 1000000

    print("fibonaci5_7")
    algoritemFib = fibonaci5_7
    start = time.time()
    rez = algoritemFib(n)
    end = time.time()
    cas = end - start
    print("Cas:", cas)
    print()

    print("fibonaci5_11")
    algoritemFib = fibonaci5_11
    start = time.time()
    rez = algoritemFib(n)
    end = time.time()
    cas = end - start
    print("Cas:", cas)
        
