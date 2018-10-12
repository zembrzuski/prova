#/bin/sh
docker-compose stop
docker-compose rm -f
cd postgres_image
docker build -t serasa-base-a .
cd ..
docker-compose up -d
