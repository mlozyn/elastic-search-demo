# Pull base image
FROM ubuntu:latest

ENV DEBIAN_FRONTEND=noninteractive \
    JAVA_HOME=/usr/lib/jvm/java-10-oracle

# Install Java
RUN VERSION=10.0.2 && \
    BUILD=13 && \
    SIG=19aef61b38124481863b1413dce1855f && \
    apt-get update && apt-get dist-upgrade -y && \
    apt-get install apt-utils ca-certificates curl -y --no-install-recommends && \
    curl --silent --location --retry 3 --cacert /etc/ssl/certs/GeoTrust_Global_CA.pem \
        --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
        http://download.oracle.com/otn-pub/java/jdk/"${VERSION}"+"${BUILD}"/"${SIG}"/jre-"${VERSION}"_linux-x64_bin.tar.gz \
        | tar xz -C /tmp && \
    mkdir -p /usr/lib/jvm && mv /tmp/jre-${VERSION} "${JAVA_HOME}" && \
    apt-get autoclean && apt-get --purge -y autoremove && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    update-alternatives --install "/usr/bin/java" "java" "${JAVA_HOME}/bin/java" 1 && \
    update-alternatives --install "/usr/bin/javaws" "javaws" "${JAVA_HOME}/bin/javaws" 1 && \
    update-alternatives --set java "${JAVA_HOME}/bin/java" && \
    update-alternatives --set javaws "${JAVA_HOME}/bin/javaws" && \
    mkdir /app

# Define working directory.
WORKDIR /app

ADD build/libs/elastic-search-demo*.jar service.jar

# Setup port
EXPOSE 8080

# Define entrypoint
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "service.jar"]

# Define default command
CMD ["bash"]