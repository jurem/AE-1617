//#include <boost/multiprecision/cpp_int.hpp>
#include <iostream>
#include <boost/multiprecision/gmp.hpp>


int main(int argc, char* argv[]) {
      int num_iters = std::atoi(argv[1]);

      using namespace boost::multiprecision;

      number<gmp_int> a(1);
      number<gmp_int> b(1);
      number<gmp_int> c(1);

      printf("Doing %d iterations\n",num_iters);

      number<gmp_int> * pa = &a;
      number<gmp_int> * pb = &b;
      number<gmp_int> * pc = &c;
      number<gmp_int> * pt;

      for (int i = 0; i<num_iters;i++){
         *pc = *pa + *pb;

         pt = pa;
         pa = pb;
         pb = pc;
         pc = pa;
      }
      pc = pb;

      *pc = *pc % 1000000;
      printf("Done\n");
      std::cout << *pc << '\n';
      
   return 0;
}





