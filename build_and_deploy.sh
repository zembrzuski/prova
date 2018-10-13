#/bin/sh
docker-compose stop
docker-compose rm -f
cd postgres_image
docker build -t serasa-base-a .
cd ..
cd base_a
docker build -t app_a .
cd ..
docker-compose up -d
