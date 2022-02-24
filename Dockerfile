#FROM openjdk:17-alpine AS gradle
#WORKDIR /app
#COPY . .
#RUN gradle bootJar
#
#FROM openjdk:17 as runtime
#WORKDIR /app
#ENV PORT 8080
#ENV SPRING_PROFILE production
#COPY --from=gradle /app/build/libs/*.jar /app/app.jar
#RUN chown -R 1000:1000 /app
#USER 1000:1000
#ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","-Dspring.profiles.active=${SPRING_PROFILE}","app.jar"]

#FROM openjdk:17 as runtime
#ADD target/ThymeleafSong-0.0.1-SNAPSHOT.jar app.jar
#ENV SPRING_PROFILE production
#EXPOSE 8080
#ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE}",  "-jar", "app.jar"]


FROM openjdk:17 as runtime
COPY target/ThymeleafSong-0.0.1-SNAPSHOT.jar app.jar
#ENV SPRING_PROFILE production
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]