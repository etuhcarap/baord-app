#!/bin/bash

cd /home/ec2-user/board-app
sudo fuser -k -n tcp 8080 || true
nohup java -jar app.jar > ./application-log 2>&1 &
