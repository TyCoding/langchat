FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/java:21-anolis

MAINTAINER langchat

WORKDIR /app

ADD target/langchat-server.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar"]

CMD ["app.jar"]