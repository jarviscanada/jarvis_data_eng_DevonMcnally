#! /bin/sh


cmd=$1
db_username=$2
db_password=$3

#Start docker

sudo systemctl status docker || sudo systemctl start docker


#docker container inspect jrvs-psql
container_status=$(docker container inspect jrvs-psql | egrep 'ExitCode' | awk  '{$1="";sub(",",""); print $0}')


case $cmd in
  create)

  # Check if the container is already created
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi


  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  #Create container
	docker pull postgres
	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lb/postgresql/data -p 5432:5432 postgres:9.6-alpine





docker volume create pgdata
	exit 0;
	;;

  start|stop)
  #check instance status; exit 1 if container has not been created
  if [ ! $container_status -eq 0 ]; then
		echo 'Container has not been created'
		exit 1;
  fi

  #Start or stop the container
	docker container $cmd jrvs-psql
	exit 0;
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac