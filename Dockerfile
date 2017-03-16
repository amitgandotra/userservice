FROM java:8-jre-alpine

# arizzini: this is used for add dependecies for wait-for-it,
#which we used to check if database listener is up and running (called from docker-compose)
RUN apk add --update bash && rm -rf /var/cache/apk/*
ADD wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

RUN mkdir /code
# Make sure to build project with -Pexecutable
ADD ./target/user-service-executable.jar /code

WORKDIR /code

EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", \
             "-Dspring.profiles.active=docker", \
     "-jar", "user-service-executable.jar"]
