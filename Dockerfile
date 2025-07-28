FROM maven:3.9-amazoncorretto-21-debian AS buildstage
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM tomcat:jre21-temurin-noble
COPY --from=buildstage /app/target/bill_mgr.war /usr/local/tomcat/webapps/bill.war
