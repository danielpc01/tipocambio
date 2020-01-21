FROM davidcaste/alpine-java-unlimited-jce:jre8

WORKDIR /opt
ADD build/libs/tipocambio-*.jar app.jar
RUN sh -c 'touch /opt/app.jar'
ADD gradle.properties gradle.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]