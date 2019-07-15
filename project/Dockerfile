# Pull base image.
#FROM oraclelinux:7-slim
FROM mysql:8.0.13
#debian:stretch-slim
#openjdk:8


# Define working directory.
RUN mkdir -p /data/cms
WORKDIR /data/cms

# Define volume:
# Mount with: -v /your/local/directory:/data/cms
VOLUME ["/data/cms"]
# Allow the host to use gradle cache, otherwise gradle will always download plugins & artifacts on every build
VOLUME ["/root/.gradle/caches/"]

#Install Java
RUN \
    apt-get update && \
    apt-get -y install unzip jre-8

# Download and install Gradle
RUN \
    cd /usr/local && \
    curl -L https://services.gradle.org/distributions/gradle-4.10.2-bin.zip -o gradle-4.10.2-bin.zip && \
    unzip gradle-4.10.2-bin.zip && \
    rm gradle-4.10.2-bin.zip

# Export some environment variables
ENV GRADLE_HOME=/usr/local/gradle-4.10.2
ENV PATH=$PATH:$GRADLE_HOME/bin JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

#----mySQL----
