FROM openjdk:11-jre-stretch

ENV LANG C.UTF-8

RUN echo "Asia/Shanghai" > /etc/timezone && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata

COPY ./alipay.jar /opt/app/app.jar
COPY docker-entrypoint.sh /usr/local/bin/

EXPOSE 8080

# 设置数据卷
VOLUME /opt/config/

ENTRYPOINT ["docker-entrypoint.sh"]