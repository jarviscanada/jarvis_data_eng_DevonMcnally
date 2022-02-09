#!/bin/bash
echo "Hello"

su centos
sudo systemctl status docker || sudo systemctl start docker
docker volume create pgdata

export PGPASSWORD='password'
docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
docker container ls -a -f name=jrvs-psql
docker ps -f name=jrvs-psql

docker container stop jrvs-psql
docker container start jrvs-psql


docker container rm jrvs-psql
