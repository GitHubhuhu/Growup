

# 服务端（sshd ）

## 安装
yum install -y curl policycoreutils-pythonopenssh-server
## 配置成开机启动
systemctl enable sshd  
## 启动
sudo systemctl start sshd
## 修改权限

```shell
cd ~
chmod 755 .ssh
chmod 600 authorized_keys
```

*ps：rsa_id.pub 、authorized_keys权限一般为644或600 ；rsa_id权限必须为600 ；.ssh目录权限一般为755或者700。*

## 添加客户端的公钥

vim authorized_keys

将客户端公钥复制到文件中，保存并退出。

*ps：复制客户端公钥时，最好用 `cat ~/.ssh/rsa_id.pub`打开文件，然后复制*

## 客户端登录

进入客户端终端，执行以下命令，登录服务器

ssh root@IP地址

# 客户端（ssh）

## 安装

Linux一般都默认有，无需安装

centos：yum install ssh

ubuntu：apt-get install ssh 

Cygwin：直接点击安装文件安装

## 生成密钥对

ssh-keygen 然后一直回车

## 获取公钥

cat ~/.ssh/id_rsa.pub

公钥信息可以复制到任何需要用到地方去。

## 服务端配置

将公钥写入到服务端的authorized_keys

## 登录

ssh root@IP地址



## 客户端ssh别名

vim ~/.ssh/config

```properties
# 我的服务器
Host tds1
HostName 10.183.172.216
Port 22
User root
IdentityFile ~/.ssh/id_rsa

# nexus服务器
Host td-nexus
HostName 10.183.145.111
Port 22
User root
```

配置完成后登录

ssh tds1

即可登录到10.183.172.216服务器。

