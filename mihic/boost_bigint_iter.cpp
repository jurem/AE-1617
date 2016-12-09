//#include <boost/multiprecision/cpp_int.hpp>
#include <iostream>
#include <boost/multiprecision/gmp.hpp>


int main(int argc, char* argv[])
{
   using namespace boost::multiprecision;
      int num_iters = std::atoi(argv[1]);
      number<gmp_int> a=1;
      number<gmp_int> b=1;
      number<gmp_int> c=1;
      //scanf("%d",&num_iters);
      printf("Doing %d iterations\n",num_iters);
      for (int i = 0; i<num_iters;i++){
         c=a+b;
         a=b;
         b=c;
      }

      c=c%1000;
      printf("Done\n");
      std::cout << c << '\n';
      
   return 0;
}




