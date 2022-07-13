## 项目开发文档
<p align="center" dir="auto">
    <img src="https://user-images.githubusercontent.com/67252967/168198779-d7a23614-d2c7-4a62-9fb0-0fe3fd40e563.png"/>
</p>
<p align="center" dir="auto">
    基于springboot的流媒体视频网站后台开发
</p>
<p align="center" dir="auto">
    	<img src="https://img.shields.io/badge/CopyRight-Bukayo-red.svg" style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
        <img src="https://img.shields.io/badge/JDK-1.8+-blue.svg" style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
        <img src="https://img.shields.io/badge/springboot-2.5.1-blue.svg" style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
        <img src="https://img.shields.io/badge/mysql-8.0.28-blue.svg"style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
        <img src="https://img.shields.io/badge/redis-6.2.6-blue.svg"style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
    	<img src="https://img.shields.io/badge/rocketmq-4.9.1-blue.svg"style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
    	<img src="https://img.shields.io/badge/elasticsearch-7.9.2-blue.svg"style="display:inline;margin-bottom:0.2rem;margin-top:0.2rem">
</p>

## 项目文档

- 文档地址：[http://bukayo.cc/docs/bilibili/](http://bukayo.cc/docs/bilibili/)
## 项目介绍
tust-bilibili是一个仿B站的视频网站后台开发项目，项目基于springboot实现。着力关注该类视频网站的的**社交属性**、**视频分片上传**与**弹幕系统设计**。本项目中多处使用redis对系统性能进行优化，包括缓存首页数据，缓存视频弹幕等，同时针对穿透、雪崩、击穿等缓存问题提出了解决方案。
## 项目演示
### 首页
![image](https://user-images.githubusercontent.com/67252967/178680660-c7eaf66b-ed20-4d4d-8047-b823a6c97f7f.png)
### 在线播放
![image](https://user-images.githubusercontent.com/67252967/178680686-1af99d81-af48-47d3-bd9e-822dcef37daf.png)
### 发送弹幕
![image](https://user-images.githubusercontent.com/67252967/178680708-81c45ea4-cf5b-46fa-9deb-71b37c1abb89.png)
### 视频上传
![image](https://user-images.githubusercontent.com/67252967/178680734-0432e622-10e8-4c14-b388-86bbe34ddc16.png)

## 组织结构
```
tust-bilibili
├── tust-bilibili-api 		-- api层
├── tust-bilibili-service -- service层
└── tust-bilibili-mapper 	-- mapper层
```
## 架构图
![image](https://user-images.githubusercontent.com/67252967/178680764-46ab78c1-e5bf-4d0e-9adc-fc878996bf4e.png)
