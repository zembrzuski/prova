#/bin/sh
docker-compose stop
cd app
docker build -t app-c .
cd ..
docker-compose rm -f
docker-compose up -d
