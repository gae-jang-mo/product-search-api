#!/bin/bash

REPOSITORY=/home/ec2-user/product-search-api/deploy
PROJECT_NAME=product-search-api

cd $REPOSITORY

echo "> build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/$PROJECT_NAME.jar

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl $PROJECT_NAME)
echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z $CURRENT_PID ]; then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/ |grep *.jar | tail -n 1)
echo ">Jar Name: $JAR_NAME"

echo ">$JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> 개장모 메인 서버 배포 합니다 !!"
nohup java -jar -Dspring.profiles.active=dev /home/ec2-user/product-search-api/deploy/zip/product-search-api-0.0.1-SNAPSHOT.jar >> /home/ec2-user/product-search-api/logs/product-search-api.log &

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/ |grep *.jar | tail -n 1)
echo ">Jar Name: $JAR_NAME"

echo ">$JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME
