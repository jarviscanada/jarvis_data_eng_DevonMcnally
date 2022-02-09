#!/bin/bash

timestamp=$(date +'%d-%m-%Y %H:%M:%S')
memory_free=$(vmstat | awk 'FNR > 2{print $4}')
cpu_idle=$(vmstat | awk 'FNR > 2{print $15}')
cpu_kernel=$(vmstat | awk 'FNR > 2{print $14}')
disk_io=$(vmstat -d | awk 'FNR > 2{print $10}')
disk_available=$(df -m | egrep 'sda2' |awk '{$1=""; print $4}')

echo 'timestamp: ' $timestamp
echo 'memory_free: ' $memory_free
echo 'cpu_idle: ' $cpu_idle
echo 'cpu_kernel: ' $cpu_kernel
echo 'disk_io: ' $disk_io
echo 'disk_available: ' $disk_available