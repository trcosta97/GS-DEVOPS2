FROM openjdk:17

WORKDIR /app

COPY target/fitVictory-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENV API_SECRET=${JWT_SECRET:my-scret-key}

CMD ["java", "-Dspring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
      "-Dspring.datasource.username=rm92800",
      "-Dspring.datasource.password=190297",
      "-Dspring.jpa.show-sql=true",
      "-Dspring.jpa.properties.hibernate.format_sql=true",
      "-Dspring.jpa.hibernate.ddl-auto=update",
      "-Dspring.datasource.driver-class-name=oracle.jdbc.OracleDriver",
      "-Dapi.security.token.secret=$API_SECRET",
      "-jar", "fitVictory-0.0.1-SNAPSHOT.jar"]