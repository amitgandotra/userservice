FROM java:8-jre-alpine

RUN apk add --update bash && rm -rf /var/cache/apk/*
ADD wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

RUN mkdir /code
# Make sure to build project with -Pexecutable
ADD ./target/userservice-executable.jar /code

WORKDIR /code

EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", \
             "-Dspring.profiles.active=docker", \
     "-jar", "user-service-executable.jar"]
