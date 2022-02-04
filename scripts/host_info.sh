#!/bin/bash
echo "Finding Hardware"

hostname=$(hostname -f)
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architechture=$(echo "$lscpu_out"  | egrep "Architecture" | awk '{print $2}' | xargs)
model_name=$(echo "$lscpu_out" | egrep 'Model name' |awk '{print $3,$4,$5}' | xargs)


echo "Host Name: "  $hostname
echo "CPU Number: " $cpu_number
echo "CPU Architechture: " $cpu_architechture
echo "Model Name: " $model_name
