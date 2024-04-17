FROM eclipse-temurin:17.0.6_10-jre

ENV VERSION="0.0.1-SNAPSHOT"


COPY target/patients-${VERSION}.jar doctors.jar

CMD ["java", "-jar", "doctors.jar"]
