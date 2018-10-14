#/bin/sh
sudo sysctl -w vm.max_map_count=262144
cd app-a/base_a
docker build -t base-a-db .
cd ../..
cd app-b/app-b
./build-and-deploy.sh
cd .. 
#cd app-c
#./build-and-deploy.sh
#cd ..
