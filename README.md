# Jenkins + EFK Stack

A [Docker Compose](https://docs.docker.com/compose/) file to launch several [Docker](https://www.docker.com/) images that will make a [Jenkins](https://www.jenkins.io/) with additional plugin installed and integrated with [Elasticsearch](https://www.elastic.co/elasticsearch) to stream Jenkins log then display it in [Kibana](https://www.elastic.co/kibana).

## Requirements

- Should build the Docker images if they aren't available.
- Should generate and run at least following container:
    - Jenkins
    - Elasticsearch
    - Kibana
- Jenkins secured with a preconfigured username and password (not the default username and password).
- Jenkins image should skip the **Setup Wizard**.
- Jenkins image should already have these following plugins:
    - Saferestart
    - Kubernetes plugin
- The services should be able to communicate with each other.

## Prerequisites

1. [Docker](https://www.docker.com/)
2. [Docker Compose](https://docs.docker.com/compose/)
3. [Java](https://www.java.com/)

## Usage

1. Start Docker Compose.
```sh
$ docker-compose up -d --build
```

2. Go to `jenkins` directory.
```sh
$ cd jenkins
```

3. Download Jenkins CLI.
```sh
$ wget http://localhost:8080/jnlpJars/jenkins-cli.jar
```

4. Create Jenkins job.
```sh
$ java -jar jenkins-cli.jar -s http://[JENKINS_USERNAME]:[JENKINS_PASSWORD]@localhost:8080 create-job hello-world < hello-world.xml
```

5. Build Jenkins job.
```sh
$ java -jar jenkins-cli.jar -s http://[JENKINS_USERNAME]:[JENKINS_PASSWORD]@localhost:8080 build hello-world
```

6. Open Kibana dashboard at `http://localhost:5601/app/kibana#/discover` to see the logs from Jenkins.

## References

- [Dockerizing Jenkins Build Logs With ELK Stack](https://ifritltd.com/2017/08/22/dockerizing-jenkins-build-logs-with-elk-stack-filebeat-elasticsearch-logstash-and-kibana/)
- [Jenkins Log Monitoring With ELK](https://dzone.com/articles/jenkins-log-monitoring-with-elk)
- [GitHub: docker-filebeat-jenkins](https://github.com/UKHomeOffice/docker-filebeat-jenkins)
- [GitHub: wait-for](https://github.com/Eficode/wait-for)
