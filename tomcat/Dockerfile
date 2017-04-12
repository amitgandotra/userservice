FROM tomcat:8.5.13-jre8
MAINTAINER Amit Gandotra(amitgandotra@hotmail.com)

#RUN apk update

COPY config/manager.xml conf/Catalina/localhost/
#COPY config/server.xml conf/
COPY config/tomcat-users.xml conf/
#COPY config/manager/context.xml webapps/manager/META-INF/
COPY userservice.war webapps/

EXPOSE  8080

CMD ["catalina.sh", "run"]
