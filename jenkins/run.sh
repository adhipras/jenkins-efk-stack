#!/bin/bash

# Start Filebeat
/etc/init.d/filebeat start

# Start Jenkins
exec bash -c /usr/local/bin/jenkins.sh
