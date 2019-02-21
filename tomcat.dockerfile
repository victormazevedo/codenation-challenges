FROM tomcat:latest
LABEL maintainer="Italo Barbosa"
ADD ./target/mapfood-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
ADD ./ambiente/tomcat-users.xml /usr/local/tomcat/conf
ADD ./ambiente/context.xml /usr/local/tomcat/manager/META-INF
COPY ./ambiente/csv /root/
WORKDIR /usr/local/tomcat
RUN su root /usr/local/tomcat/bin/startup.sh
EXPOSE 8080