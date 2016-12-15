#include <iostream>
#include <boost/multiprecision/gmp.hpp>

//ce se za mnozenje matrik uporabi strasena 
//bo najvrjetneje delalo se hitreje
//saj so mnozenja velikih stevil zahtevna

void fibonaci(long n){
    using namespace boost::multiprecision;
    
    number<gmp_int> a1, a2, a4;
    number<gmp_int> b1, b2, b4;
    number<gmp_int> a22;
	number<gmp_int> c2;
	number<gmp_int> c1;
    
    a1 = 1;
    a2 = 1;
    a4 = 0;
    
    b1 = 1;
    b2 = 0;
    b4 = 1;

	a22 = 0;
    
    long k = n;
    long ost = 0;
    while (k>0){
        ost = k%2;
        k = k/2;
        
        if (ost == 1){
            b1 = b1 * a1 + b2 * a2;
			c2=b2;
            b2 = b2 * a1 + b4 * a2;
            b4 = c2 * a2 + b4 * a4;
        }

        a22 = a2 * a2;
		c1=a1;
        a1 = a1 * a1 + a22;
        a2 = c1 * a2 + a2 * a4;
        a4 = a22 + a4 * a4;
    }
    b2 = b2%1000000;
    std::cout << b2 << '\n';   
}

int main(){
    fibonaci(9178575);
    return 0;
}
