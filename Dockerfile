FROM java:8

ENV LC_ALL C.UTF-8
ENV LANG C.UTF-8
ENV TZ asia/Shanghai

WORKDIR /opt

ADD target/mb-backend.jar /opt/mb-backend.jar
ENV JAVA_OPTS="\
-server \
-Xmx1.5g \
-Xms1.5g \
-Xss1m \
-Xmn1g"
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","-Dfile.encoding=utf-8","/opt/mb-backend.jar"]
EXPOSE 8080