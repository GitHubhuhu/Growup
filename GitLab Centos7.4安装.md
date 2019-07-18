# Centos7.4安装GitLab服务

依赖

*  sshd  ssh登录，非必须，如果不配置ssh，则使用http方式
*  postfix  发送邮件，非必须，不做相关配置无法使用邮箱服务
*  防火墙-端口管理，必须

## 依赖安装

使用root用户安装

如果不是root用户，所有命令前需要添加sudo

### sshd 安装

```shell
## 安装
yum install -y curl policycoreutils-pythonopenssh-server
## 配置成开机启动
systemctl enable sshd  
## 启动
sudo systemctl start sshd
## 验证
cd ~/.ssh/
### 在authorized_keys文件夹里添加客户端的公钥信息
vim authorized_keys
### 修改authorized_keys权限
chmod 600 authorized_keys
### 进入客户端终端，执行以下命令，登录服务器
ssh root@IP地址

```

### postfix 安装



```shell
## 安装
yum install postfix
## 设置成开机启动
systemctl enable postfix
## 启动
systemctl start postfix
```

## GitLab安装

### 1. 下载

下载站点，国内镜像站：https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el7/

选择下载版本，选择最新的，2019年7月9日最新版为`gitlab-ce-12.0.3-ce.0.el7.x86_64.rpm`

如果在桌面环境下载直接浏览器下载即可。

如果在终端环境下载请用以下命令：

```shell
wget https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el7/gitlab-ce-12.0.3-ce.0.el7.x86_64.rpm
```

### 2. 安装

```shell
rpm -i gitlab-ce-12.0.3-ce.0.el7.x86_64.rpm
```

成功页面如下：

![1562658404544](C:\Users\lwx745500\AppData\Roaming\Typora\typora-user-images\1562658404544.png)



### 3. 修改配置

尤其是注意端口必须是没有使用过的。

```shell
vim /etc/gitlab/gitlab.rb
```

#### 修改内容-配置IP和端口

PS:这里配置的ip和端口主要是Nginx用的。

使用关键字`external_url`查询，该地址是gitlab的web主页地址，可以配置成如下方式：

1.  http://10.183.172.216   指定IP，使用默认80端口
2.  http://10.183.172.216:8085  指定ip和端口
3.  http://git.xx.com  指定域名，使用默认80端口

```shell
## GitLab URL
##! URL on which GitLab will be reachable.
##! For more details on configuring external_url see:
##! https://docs.gitlab.com/omnibus/settings/configuration.html#configuring-the-external-url-for-gitlab
external_url 'http://10.183.172.216'

```

#### 修改内容-配置unicorn端口

1. 主要修改端口

```shell

 unicorn['enable'] = true
 unicorn['worker_timeout'] = 60
###! Minimum worker_processes is 2 at this moment
###! See https://gitlab.com/gitlab-org/gitlab-ce/issues/18771
 unicorn['worker_processes'] = 3

### Advanced settings
# unicorn['listen'] = 'localhost'
 unicorn['port'] = 8091
# unicorn['socket'] = '/var/opt/gitlab/gitlab-rails/sockets/gitlab.socket'
# unicorn['pidfile'] = '/opt/gitlab/var/unicorn/unicorn.pid'
# unicorn['tcp_nopush'] = true
# unicorn['backlog_socket'] = 1024

```

### 4. 重启和启动

```shell
## 加载配置
gitlab-ctl reconfigure
## 重启
gitlab-ctl restart

```



### 5. 访问

http://10.183.172.216/

打开首页直接设置 `root`用户密码。如下图



![1562659399760](C:\Users\lwx745500\AppData\Roaming\Typora\typora-user-images\1562659399760.png)