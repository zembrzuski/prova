#/bin/sh
docker-compose stop
cd app
docker build -t app-c .
cd ..
# docker-compose rm -f
docker-compose up -d elasticsearch
sleep 60
docker-compose up -d
