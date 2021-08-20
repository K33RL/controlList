FROM tomcat:latest
ENV TZ=Europe/Moscow
COPY target/ROOT.war /usr/local/tomcat/webapps/