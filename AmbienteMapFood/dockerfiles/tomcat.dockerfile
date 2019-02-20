FROM tomcat:latest
LABEL maintainer="Italo Barbosa"
COPY ./target/mapfood-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
VOLUME /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat
RUN su root /usr/local/tomcat/bin/startup.sh
EXPOSE 8080