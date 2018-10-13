#/bin/sh
sudo sysctl -w vm.max_map_count=262144
cd app-a
./build-and-deploy.sh
cd ..
cd app-b
./build-and-deploy.sh
cd .. 
cd app-c
./build-and-deploy.sh
cd ..
