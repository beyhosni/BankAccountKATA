# Étape de construction
FROM maven:4.0.0-openjdk-21 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et le dossier source
COPY pom.xml .
COPY src ./src

# Construire l'application (sans exécuter les tests)
RUN mvn clean package -DskipTests

# Étape d'exécution
FROM openjdk:21-jre-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR compilé depuis l'étape de construction
COPY --from=build /app/target/bankaccountkata-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
