#include <iostream>
#include <boost/multiprecision/gmp.hpp>

int main(int argc, char* argv[])
{
   using namespace boost::multiprecision;
      int num_iters = std::atoi(argv[1]);
      number<gmp_int> a=1;
      number<gmp_int> b=1;
      printf("Calculating %dth fibbonaci number\n",num_iters);
      num_iters = num_iters - 1;
      for (int i = 0; i<num_iters;i++){
         a+=b;
         std::swap(a,b);
      }

      b=b%1000000;
      printf("Done\n");
      std::cout << b << '\n';
      
   return 0;
}