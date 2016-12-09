#!/bin/bash

# Isce, pri katerem fibonacciju zmanjka pomnilnika
# rezultate izpise v csv datoteko

# obmocje meritev (MB pomnilnika za javo)
beg=32
step=32
end=512
###########################################################
javac Fibonacci.java

echo "testing fibonacci with BigInteger memory consumption"
starting_time=$(date +'%d%m-%H%M')
echo "starting time: $starting_time"
filename="out_v1_${starting_time}.csv"
echo "heap, fib" | tee $filename
result=10
for i in $(seq $beg $step $end); do
	t=$(date +%s)
	result="$(java -Xss512m -Xmx${i}m Fibonacci $result)"
	t=$(( $(date +%s) - t))
	echo "$i mb, ${result}th fibonacci number, total running time $t s"
	echo "$i, $result" >> $filename
done

