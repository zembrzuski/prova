FROM openjdk:8-jdk as builder
COPY . /app
WORKDIR /app
RUN ./mvnw package -DskipTests=true

FROM openjdk:8-jre
COPY --from=builder /app/target/app-b-0.0.1-SNAPSHOT.jar /app/application.jar
RUN mkdir -p /root/.postgresql
COPY --from=builder /app/root.crt /root/.postgresql/root.crt
CMD java -jar -Dspring.profiles.active=container /app/application.jar

