#/bin/sh
docker-compose stop
# docker-compose rm -f
cd app-b
docker build -t app_b .
cd ..
docker-compose up -d
