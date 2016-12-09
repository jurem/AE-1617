##Hiter in eleganten algoritem (fibonaci5_11)

def fibonaciMatrike(n):
    (a1, a2, a3, a4) = (1, 1, 1, 0)
    (b1, b2, b3, b4) = (1, 0, 0, 1)

    k = n
    while k>0:
        (k,ost) = divmod(k, 2)
        if ost == 1:
            (b1, b2, b3, b4) = (b1 * a1 + b2 * a3, b1 * a2 + b2 * a4,
                                b3 * a1 + b4 * a3, b3 * a2 + b4 * a4)
            
        (a1, a2,
         a3, a4)=(a1 * a1 + a2 * a3, a1 * a2 + a2 * a4,
                  a3 * a1 + a4 * a3, a3 * a2 + a4 * a4)

    return b3


########################################
########################################

import time

def test():
    n = 10000000
    m = 10

    print("fibonaciMatrike")
    print("n =",n)
    algoritemFib = fibonaciMatrike
    start = time.time()
    rez = algoritemFib(n)
    end = time.time()
    cas = end - start
    print("Cas:", cas)
    print("Rezultat "+str(m)+" mesta:",rez%(10**m))

if __name__ == "__main__":
    test()

##Zgornji algoritem v Cpp (nekaj ne dela, napacna uporaba knjiznice?)
"""
#include <iostream>
#include <boost/multiprecision/gmp.hpp>

//ce se za mnozenje matrik uporabi strasena 
//bo najvrjetneje delalo se hitreje
//saj so mnozenja velikih stevil zahtevna

void fibonaci(long long int n){
    using namespace boost::multiprecision;
    
    number<gmp_int> a1, a2, a3, a4;
    number<gmp_int> b1, b2, b3, b4;
    
    a1 = 1;
    a2 = 1;
    a3 = 1;
    a4 = 0;
    
    b1 = 1;
    b2 = 0;
    b3 = 0;
    b4 = 1;
    
    long long int k = n;
    long long int ost = 0;
    while (k>0){
        ost = k%2;
        k = k/2;
        
        if (ost == 1){
            b1 = b1 * a1 + b2 * a3;
            b2 = b1 * a2 + b2 * a4;
            b3 = b3 * a1 + b4 * a3;
            b4 = b3 * a2 + b4 * a4;
        }
        
        a1 = a1 * a1 + a2 * a3;
        a2 = a1 * a2 + a2 * a4;
        a3 = a3 * a1 + a4 * a3;
        a4 = a3 * a2 + a4 * a4;
    }

    b3 = b3%1000000;
    std::cout << b3 << '\n';   
}

int main(){
    fibonaci(10);
    return 0;
}
"""


