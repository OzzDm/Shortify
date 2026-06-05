# This Approach requires user to build .war on local machine and then run the docker compose in order to get the application running.
# FROM tomcat:10.1-jdk21
# RUN rm -rf /usr/local/tomcat/webapps/*
# COPY target/*.war /usr/local/tomcat/webapps/ROOT.war
# EXPOSE 8080

# Introducing Multi-Stage Docker Build
# In this approach:
# no local Maven build required
# no WAR committed to GitHub
# Docker builds everything
# --------------------
# Stage 1 - Build WAR
# --------------------
FROM maven:3.9.11-eclipse-temurin-21 AS shortify-builder

WORKDIR /shortify

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# --------------------
# Stage 2 - Run Tomcat
# --------------------
FROM tomcat:10.1-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=shortify-builder /shortify/target/*.war \
    /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
