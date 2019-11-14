#!/bin/bash
set -eo pipefail

exec java -Xmx512m -Xms512m -jar /opt/app/app.jar \
          --spring.config.additional-location=/opt/config/
