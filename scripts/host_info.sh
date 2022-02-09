#!/bin/bash

if [ $# -ne 5 ]; then
  echo 'Arguments required: psql_host, psql_port, db_name, psql_user, psql_password'
  exit 1
fi

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

lscpu_out=$(lscpu)
meminfo=$(cat /proc/meminfo)

hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture" | awk '{print $2}' | xargs)
model_name=$(echo "$lscpu_out" | egrep 'Model name' |awk '{$1=$2="";print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep 'CPU MHz' |awk '{$1=$2="";print $0}' | xargs)
L2_cache=$(echo "$lscpu_out" | egrep 'L2 cache' |awk '{$1=$2="";sub("K","");print $0}' | xargs)
total_mem=$(echo "$meminfo" | egrep 'MemTotal' |awk '{$1="";sub("kB","");print $0}' | xargs)
timestamp=$(date +'%d-%m-%Y %H:%M:%S')

insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, model_name, cpu_mhz, L2_cache, total_mem, timestamp)
 VALUES ($hostname, $cpu_number, $cpu_architecture, $model_name, $cpu_mhz, $L2_cache, $total_mem, $timestamp);"

echo "Host Name: "  $hostname
echo "CPU Number: " $cpu_number
echo "CPU Architecture: " $cpu_architecture
echo "Model Name: " $model_name
echo "CPU MHz: " $cpu_mhz
echo "L2 Cache: " $L2_cache
echo "total Memory: " $total_mem
echo "Timestamp: " $timestamp

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c $insert_stmt
exit 0
