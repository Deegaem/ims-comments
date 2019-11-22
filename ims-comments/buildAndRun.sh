#!/bin/sh
mvn clean package && docker build -t org.digam/ims-comments .
docker run -p 8083:8080 --name ims-comments org.digam/ims-comments