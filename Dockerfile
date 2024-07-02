# telechargement de maven afin de construire la solution
# JDK = Java Development Kit
FROM maven:3.9.7-eclipse-temurin-17 AS build

# mkdir app & cd
WORKDIR /app

# je copie le projet
COPY . .

# telecharment des dependance & compilation
RUN mvn clean package -DskipTests

# utilisation d'un JRE pour executer le code
# Java Runtime Environment
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.war app.war

EXPOSE 8081
# commande qui sera executé lors du lancement du container
ENTRYPOINT ["java","-jar","/app/app.war"]