FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD PROJECT_JAR app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
