# Set the base image
FROM jenkins/jenkins:lts-jdk11

# Set new Jenkins user
ENV JENKINS_USERNAME adhipras

# Set new Jenkins password
ENV JENKINS_PASSWORD password123

# Skip Jenkins Setup Wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Copy Groovy script for initializing the new user
COPY init.groovy /usr/share/jenkins/ref/init.groovy.d/

# Install Jenkins plugins
RUN /usr/local/bin/install-plugins.sh saferestart kubernetes

# Set the user
USER root

# Download and install Filebeat
RUN curl -L https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.8.18-amd64.deb -o /tmp/filebeat-6.8.18-amd64.deb
RUN dpkg -i /tmp/filebeat-6.8.18-amd64.deb

# Copy Filebeat configuration file
COPY filebeat.yml /etc/filebeat/filebeat.yml

# Install dependencies
RUN apt update && apt install -y netcat

# Download wait-for
RUN curl -L https://raw.githubusercontent.com/eficode/wait-for/master/wait-for -o /wait-for

# Copy script
COPY run.sh /

# Make scripts executable
RUN chmod +x /run.sh
RUN chmod +x /wait-for

# Expose the ports
EXPOSE 8080/tcp 8443/tcp 50000/tcp

# Run command
CMD ["/bin/bash", "-c", "./run.sh"]