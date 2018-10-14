#/bin/sh
sudo sysctl -w vm.max_map_count=262144
cd app-a/base_a
docker build -t app_a .
cd ../..
cd app-b/app-b
docker build -t app_b .
cd .. 
#cd app-c
#./build-and-deploy.sh
#cd ..
