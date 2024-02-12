# build app
FROM maven:3.9.6-eclipse-temurin-21-alpine as app-buider
COPY . /app
WORKDIR /app
RUN mvn -B clean package -P prod -D skipTests

# create container image
FROM eclipse-temurin:21-jre-alpine

# update packages (for security) + curl and jq for health check
#RUN apk update && apk upgrade && apk --no-cache add curl
RUN apk --update --no-cache add curl jq

VOLUME /tmp
ARG JAR_FILE=target/*.jar

# copy app
COPY --from=app-buider /app/${JAR_FILE} /app/app.jar

EXPOSE 8080

# init command
ENTRYPOINT ["sh", "-c", "java -XshowSettings:system -jar /app/app.jar ${0} ${@}"]

# #### OLD BUILD COMMANDS FROM PREVIOUS FILE
#FROM eclipse-temurin:21-jre-alpine
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["sh", "-c", "java -XshowSettings:system -jar /app.jar ${0} ${@}"]
