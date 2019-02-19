FROM tomcat:latest
LABEL maintainer="Italo Barbosa"
COPY ./deploy /usr/local/tomcat/webapps
VOLUME /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat
RUN su root /usr/local/tomcat/bin/startup.sh
EXPOSE 8080