# 指定是基于哪个基础镜像
FROM java:8

# 作者信息
MAINTAINER gustavo

# 挂载点声明
VOLUME /tmp

# 将本地的一个文件或目录，拷贝到容器的文件或目录里
ADD /target/Blog_Spring.jar Blog_Spring.jar

#shell脚本
RUN bash -c 'touch /Blog_Spring.jar'

# 将容器的8000端口暴露，给外部访问。
EXPOSE 9004

# 当容器运行起来时执行使用运行jar的指令
ENTRYPOINT ["java", "-jar", "Blog_Spring.jar"]
