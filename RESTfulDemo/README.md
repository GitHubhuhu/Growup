
## 上传图片 RESTful H2 Spring-boot Http 请求方式


## 引入 Developer tools 

    
    eclipse 修改后保存,自动编译就会触发服务重启.
    idea 需要 CMD+F9 编译后触发服务重启
    
    参考文档:http://blog.csdn.net/qiutongyeluo/article/details/51918888

    
    
## Spring Boot 配置加载顺序

    1. 命令行参数。
    2. 通过 System.getProperties() 获取的 Java 系统参数。
    3. 操作系统环境变量。
    4. 从 java:comp/env 得到的 JNDI 属性。
    5. 通过 RandomValuePropertySource 生成的“random.*”属性。
    6. 应用 Jar 文件之外的属性文件(application.properties)。
    7. 应用 Jar 文件内部的属性文件(application.properties)。
    8. 在应用配置 Java 类（包含“@Configuration”注解的 Java 类）中通过“@PropertySource”注解声明的属性文件。
    9. 通过“SpringApplication.setDefaultProperties”声明的默认属性。
    
    >>>>这意味着，如果Spring Boot在优先级更高的位置找到了配置，那么它就会无视低级的配置。
    
## H2 数据库连接
    
    默认地址: /h2-console  WEB控制台地址 ; 自定义地址 spring.h2.console.path=
    EG: http://localhost:8080/h2-console 
    如果没有 Developer tools , web控制台是关闭的,需要配置:spring.h2.console.enabled=true ,注意,生产环境必须使用false.
    
    #内存模式 数据存在内存中,重启丢失
    #spring.datasource.url=jdbc:h2:mem:mydb
    
    #文件模式,数据存在指定的文件中
    spring.datasource.url=jdbc:h2:file:/srv/tmp/h2springboot
    
    web控制台连接方式: 
        JDBC URL : jdbc:h2:/srv/tmp/h2springboot
        User Name : sa
    
    
## jOOQ 从数据库生成java代码
     
   http://www.jooq.org/