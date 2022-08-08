@echo off
call mvn clean package
call docker build -t com.mycompany/EPlay-Prime-war .
call docker rm -f EPlay-Prime-war
call docker run -d -p 9080:9080 -p 9443:9443 --name EPlay-Prime-war com.mycompany/EPlay-Prime-war