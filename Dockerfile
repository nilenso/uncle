FROM eclipse-temurin:21
RUN mkdir /opt/uncle/
COPY ./webserver/build/libs/webserver-all.jar /opt/uncle/webserver-all.jar
CMD ["java", "-jar", "webserver-all.jar"]
