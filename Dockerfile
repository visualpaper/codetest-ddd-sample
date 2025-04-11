FROM maven:3.8.1-jdk-8-slim AS build

COPY ./ /home/app/
RUN mvn -f /home/app/pom.xml clean package


FROM tomcat:9.0-jdk8-corretto

COPY --from=build /home/app/codetest-umejima-impl/target/codetest-umejima.war /usr/local/tomcat/webapps/
