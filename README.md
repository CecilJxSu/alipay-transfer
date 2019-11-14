# 目录结构
```
Docker/
---- alipay.jar
---- docker-compose.yml
---- docker-entrypoint.sh
---- Dockerfile

src/

mvnw
mvnw.cmd

pom.xml
```

## Docker目录
包含编译后的文件 alipay.jar，docker相关脚本，用于编译 docker 镜像和管理 docker 容器。

部署时，将整个目录上传到服务器。

## src目录
项目源码

## mvnw、mvnw.cmd
*nix 环境执行 mvnw 命令，Windows 环境执行 mvnw.cmd。

用于配置maven环境，或者手动安装maven环境。

## pom.xml
maven 管理项目的依赖

# 编译
## 安装 maven 环境
```bash
./mvnw
```

## 编译
```bash
mvn clean package -DskipTests
```

编译完成后，会自动将项目打包成alipay.jar文件，然后移动到Docker目录下

# 部署
以下命令以 CentOS 7 为例，其它操作系统请查询相关命令
## 配置Docker、docker-compose环境
参考 http://get.daocloud.io/ 的 "Docker 高速下载" 和 "Compose(Fig) 下载"

执行以下命令，检查是否安装成功
```bash
docker

docker-compose
```

## 启动docker
```bash
# 启动 docker 服务
systemctl start docker.service

# 设置开机启动
systemctl enable docker.service
```

## 上传文件到服务器
```bash
# 把整个 Docker 上传到服务器上
scp -r Docker/ root@xxx.xxx.xxx.xxx:/root/Docker/

# 登陆服务器
ssh root@xxx.xxx.xxx.xxx

# 创建 cert 目录，存放证书位置
mkdir -p /root/cert
```

## 编译和启动项目
```bash
# 进入Docker目录
cd /root/Docker/

# 编译本地镜像
docker-compose build

# 后台启动镜像
docker-compose up -d
```


启动完成后，访问 POST http://xxx.xxx.xxx.xxx:8080/api/transfer，例子

```bash
curl -H "Accept: application/json" -H "Content-type: application/json" \
     -X POST \
     -d '{
         "private_key": "<your_private_key>",
         "app_id": "<your_app_id>",
         "out_biz_no": "564322234400",
         "payee_type": "ALIPAY_LOGONID",
         "payee_account": "<payee_account>",
         "amount": "0.1",
         "app_cert_path": "appCertPublicKey.crt",
         "alipay_cert_path": "alipayCertPublicKey_RSA2.crt",
         "alipay_root_cert_path": "alipayRootCert.crt"
     }' \
     http://xxx.xxx.xxx.xxx:8080/api/transfer
```

## 设置开机启动docker-compose
```bash
# 编辑 rc.local 文件
vim /etc/rc.d/rc.local

# rc.local 文件后添加以下命令
/usr/local/bin/docker-compose -f /root/Docker/docker-compose.yml up -d
```

# 重新编译和部署

## 重新编译代码
```bash
mvn clean package -DskipTests
```
编译后，只上传 alipay.jar 文件即可，其余可不用上传

## 重新编译镜像
```bash
# 进入 Docker 项目
cd /root/Docker

# 编译镜像
docker-compose build

# 重启服务
docker-compoes restart
```