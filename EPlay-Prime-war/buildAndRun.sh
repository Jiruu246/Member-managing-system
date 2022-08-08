#!/bin/sh
mvn clean package && docker build -t com.mycompany/EPlay-Prime-war .
docker rm -f EPlay-Prime-war || true && docker run -d -p 9080:9080 -p 9443:9443 --name EPlay-Prime-war com.mycompany/EPlay-Prime-war