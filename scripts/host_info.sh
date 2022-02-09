#!/bin/bash
echo "Finding Hardware"

hostname=$(hostname -f)
lscpu_out=`lscpu`
meminfo=$(cat /proc/meminfo)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture" | awk '{print $2}' | xargs)
model_name=$(echo "$lscpu_out" | egrep 'Model name' |awk '{$1=$2="";print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep 'CPU MHz' |awk '{$1=$2="";print $0}' | xargs)
L2_cache=$(echo "$lscpu_out" | egrep 'L2 cache' |awk '{$1=$2="";print $0}' | xargs)
total_mem=$(echo "$meminfo" | egrep 'MemTotal' |awk '{$1="";print $0}' | xargs)
timestamp=$(date +'%d-%m-%Y %H:%M:%S')



echo "Host Name: "  $hostname
echo "CPU Number: " $cpu_number
echo "CPU Architecture: " $cpu_architecture
echo "Model Name: " $model_name
echo "CPU MHz: " $cpu_mhz
echo "L2 Cache: " $L2_cache
echo "total Memory: " $total_mem
echo "Timestamp: " $timestamp
 
exit 0
