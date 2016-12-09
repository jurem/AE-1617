#!/bin/bash

# meri porabo pomnilnika ko racunamo fibonaccija z memoizacijo (naivno)

# pomnilnik (povecaj ce nastavis vecje stevilke za drugi loop)
mem=2048
# za majhne stevilke for (n=beg1; n<=end1; n+=step1)
beg1=100
step1=100
end1=10000

# za velike stevilke for (n=beg2; n<=end2; n+=step2)
beg2=20000
step2=10000
end2=180000
###########################################################
javac Fibonacci2.java

echo "testing fibonacci with BigInteger memory consumption"
starting_time=$(date +'%d%m-%H%M')
echo "starting time: $starting_time"
filename="out_v2_${starting_time}.csv"
java -Xss512m -Xmx${mem}m Fibonacci2 $beg1 $step1 $end1 $beg2 $step2 $end2 | tee $filename
