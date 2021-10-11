FROM openjdk:11
VOLUME /tmp
EXPOSE 8080   
ADD ./target/appweb-1.0.jar appweb.jar
ENTRYPOINT ["java", "-jar", "/appweb.jar"]
        