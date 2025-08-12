#!/bin/bash

rm -rf /home/ec2-user/board-app
mkdir /home/ec2-user/board-app
cd /home/ec2-user/board-app
sudo fuser -k -n tcp 8080 || true
nohup java -jar /home/ec2-user/board-app/app.jar > application-log 2>&1 &
