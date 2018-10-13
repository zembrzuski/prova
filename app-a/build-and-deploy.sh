#/bin/sh
docker-compose stop
# docker-compose rm -f
cd postgres_image
docker build -t base-a-db .
cd ..
cd base_a
docker build -t app_a .
cd ..
docker-compose up -d
