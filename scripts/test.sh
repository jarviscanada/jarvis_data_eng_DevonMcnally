#!/bin/bash

container_status=$(docker container inspect jrvs-psql | egrep 'ExitCode' | awk  '{$1="";sub(",",""); print $0}')

if [ "$container_status" -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi