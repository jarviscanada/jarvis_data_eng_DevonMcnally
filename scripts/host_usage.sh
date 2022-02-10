#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

hostname=$(hostname)
timestamp=$(date +'%d-%m-%Y %H:%M:%S')
memory_free=$(vmstat | awk 'FNR > 2{print $4}')
cpu_idle=$(vmstat | awk 'FNR > 2{print $15}')
cpu_kernel=$(vmstat | awk 'FNR > 2{print $14}')
disk_io=$(vmstat -d | awk 'FNR > 2{print $10}')
disk_available=$(df -m | egrep 'sda2' |awk '{$1=""; print $4}')
export PGPASSWORD='password'
host_id=$(psql -qtAX -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "SELECT id FROM host_info WHERE hostname='$hostname';")


echo 'timestamp: ' $timestamp
echo 'memory_free: ' $memory_free
echo 'cpu_idle: ' $cpu_idle
echo 'cpu_kernel: ' $cpu_kernel
echo 'disk_io: ' $disk_io
echo 'disk_available: ' $disk_available

insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
 VALUES ('$timestamp', '$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"




psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit 0
