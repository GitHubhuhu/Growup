# Nexus 配置

版本：Sonatype Nexus Repository Manager OSS 3.15.2-01



## 配置Nexus中央仓库地址

1. 设置-->Repositories-->maven-central

![1566184195326](../.image/1566184195326.png)

2. 设置中央仓库地址

   * 华为镜像：https://mirrors.huaweicloud.com/
   * 阿里镜像：http://maven.aliyun.com/nexus/content/groups/public
   * OsChaina：http://maven.oschina.net/content/groups/public/

   ![1566184302675](C:\Users\lwx745500\AppData\Roaming\Typora\typora-user-images\1566184302675.png)

3. 保存

   保存完了之后，就代码配置完成了。

4. 配置 setting.xml

   将如下`url`中ip和端口换成自己的nexus地址即可。

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
   	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
   
   	<localRepository>D:\maven\repo</localRepository>
   
   
   	<mirrors>
   		<mirror>
   			<id>huawei-mirror</id>
   			<name>huawei-mirror nexus</name>
   			<mirrorOf>central</mirrorOf>
   			<url>http://10.183.145.111:8081/repository/maven-central/</url>
   		</mirror>
   	</mirrors>
   	
   </settings>
   ```

   

5. 验证nexus是否能从指定的仓库同步数据

   新建一个maven项目，在pom.xml中引入一个私服中没有的包

   比如：

   ```xml
   <dependency>
       <groupId>org.apache.storm</groupId>
       <artifactId>storm-core</artifactId>
       <version>1.2.3</version>
       <scope>provided</scope>
   </dependency>
   ```

   然后执行package，如果包同步完成，浏览器打开私服地址

   http://10.183.145.111:8081/#browse/welcome

   搜索包名：storm

   在列表中出现storm相关包，则表示成功啦。

   ![1566184847811](C:\Users\lwx745500\AppData\Roaming\Typora\typora-user-images\1566184847811.png)

