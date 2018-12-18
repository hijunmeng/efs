# 第一条指令
# FROM openjdk:8-jdk-alpine
FROM frolvlad/alpine-oraclejdk8:slim
# 作者信息
MAINTAINET hwj
# 卷
VOLUME /tmp
ARG JAR_FILE
# 将文件复制并命名
ADD ${JAR_FILE} app.jar
# 暴露端口
EXPOSE 8082
# 容器启动后执行的指令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
