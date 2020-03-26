# 基于哪个镜像
FROM adoptopenjdk/openjdk8:latest

# 将本地文件夹挂载到当前容器
VOLUME /tmp

ADD test.jar test.jar
RUN sh -c 'touch /test.jar'

ENV JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms1024m -Xmx1024m -XX:NewSize=1024m -XX:MaxNewSize=1024m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:+DisableExplicitGC"
#修改默认时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 配置容器启动后执行的命令
ENTRYPOINT ["sh", "-c" , "java -Xms1024m -Xmx1024m -Djava.security.egd=file:/dev/./urandom -jar /test.jar" ]