## Clean docker
docker rm -vf $(docker ps -a -q)
docker rmi -f $(docker images -a -q)

##Build docker
docker build -t "mytomcat:latest" .

##Run Docker
docker run --name mytomcat1 -it --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=container mytomcat:latest

docker run --name mytomcat1 -it --rm -p 8080:8080 mytomcat:latest

## Tomcat unix console

docker exec -i -t mytomcat1 /bin/sh

docker exec -i -t mytomcat1 /bin/bash

##check user-services
curl http://localhost:8080/userservice/v1/users

curl -i -X GET http://localhost:8080/userservice/v1/users

curl -H "Accept:application/json" http://localhost:8080/userservice/v1/users | python -m json.tool

curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/userservice/v1/users -d '{"user" : {"userId" : "create-user-1","email" : "create-user-1@ibps.com","username" : "create-user-1","firstName" : "create-user-1","lastName" : "create-user-1","locale" : "en_US","phones" : [ {"phoneType" : "H","phoneNo" : "111-222-3333"}, {"phoneType" : "M","phoneNo" : "555-222-3333"} ]}}'
