FROM java:8
MAINTAINER gustavo
COPY *.jar /app.jar

CMD ["--server.port=9004"]

EXPOSE 9004

ENTRYPOINT ["java","-jar","/app.jar"]
