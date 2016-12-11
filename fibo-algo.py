#coding: utf-8
#avtor: Tadej Ciglarič
from decimal import *
import math,time

def fibonacci_(n,sq5, fi):
    return (fi**n-(1-fi)**n)/sq5

def fibonacci_formula(n):
    getcontext().prec = 100
    sq5=Decimal(5)**Decimal(0.5)
    fi=(1+sq5)/2
    tmp=fibonacci_(n,sq5,fi)
    print("prec",int(tmp.log10()))
    getcontext().prec = int(tmp.log10())+1
    sq5=Decimal(5)**Decimal(0.5)
    fi=(1+sq5)/2
    return fibonacci_(n,sq5,fi)

def fibonacci_iter(n):
    a,b=1,1
    for i in range(n):
        a,b=b,a+b
    return a

def test_iter():
    t=time.clock()
    f=fibonacci_iter(100000)
    t=time.clock()-t
    print(f,"\n time: ",t)

def test_formula():
    t=time.clock()
    a=fibonacci_formula(10000)
    t=time.clock()-t
    b=a.quantize(Decimal('1.'))
    print(b,"\n time: ",t)

if __name__=="__main__":
    test_formula()
    test_iter()