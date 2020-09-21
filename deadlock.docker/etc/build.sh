#!/bin/bash

echo cleanup

if [ -d temp ]; then
  if [ "$(ls -A temp)" ]; then
    echo deleting contents of temp
    rm -rd temp/*
  fi
else
  mkdir temp
fi

echo copy files to build

cp ../../deadlock.ear/target/deadlock.ear-1.0-SNAPSHOT.ear temp/
if [ ! $? ]; then
  echo error copying
  exit 1
fi

echo build wfly image

docker image build --tag local:wfly20 --no-cache --file d1 .
if [ ! $? ]; then
  echo error creating wfly image
  exit 1
fi

echo build wfly image with deployment

docker image build --tag local/deadlock:latest --no-cache --file d2 .
if [ ! $? ]; then
  echo error creating wfly image with deployment
  exit 1
fi

echo docker container run -p 8080:8080 local/deadlock:latest
