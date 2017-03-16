## Commands


docker build -t "ibps/usersvcs:latest" .

docker run -d -p 8080:8080 --name userservice1 ibps/usersvcs

docker run  -p 8080:8080 --name userservice1 ibps/usersvcs

docker  logs -f userservice1

curl http://localhost:8080/userservice/v1/users

curl -i -X GET http://localhost:8080/userservice/v1/users

curl -H "Accept:application/json" http://localhost:8080/userservice/v1/users | python -m json.tool

curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/userservice/v1/users -d '{"user" : {"userId" : "create-user-1","email" : "create-user-1@ibps.com","username" : "create-user-1","firstName" : "create-user-1","lastName" : "create-user-1","locale" : "en_US","phones" : [ {"phoneType" : "H","phoneNo" : "111-222-3333"}, {"phoneType" : "M","phoneNo" : "555-222-3333"} ]}}'


## endpoints

#### Actuator End points
http://localhost:8080/userservice/info

http://localhost:8080/userservice/health

http://localhost:8080/userservice/metrics

http://localhost:8080/userservice/trace

----


http://localhost:8080/userservice/beans

http://localhost:8080/userservice/configprops

http://localhost:8080/userservice/autoconfig

http://localhost:8080/userservice/dump

http://localhost:8080/userservice/env

curl -X POST http://localhost:8080/userservice/shutdown

http://localhost:8080/userservice/heapdump

---  http://localhost:8080/userservice/jolokia

http://localhost:8080/userservice/loggers

http://localhost:8080/userservice/loggers/ROOT

http://localhost:8080/userservice/loggers/com.ibps.openapi.service.UserService

curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://localhost:8080/userservice/loggers/com.ibps.openapi.service.UserService

http://localhost:8080/userservice/auditevents

http://localhost:8080/userservice/mappings

http://localhost:8080/userservice/trace