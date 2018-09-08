## BOS物流管理系统介绍
----------
该BOS物流管理系统是基于某物流公司的C/S端程序（由C++开发），经二次改造开发而成，主要完成的模块有管理员权限规划、取派员的增加和作废、取派员信息修改、取派员的查询、定区和分区的添加、区域规划、角色管理、CRM系统和该BOS系统的对接、区域数据的图表显示等。<br>
## 项目技术选型介绍
----
因为该系统比较复杂和对安全性要求较高，所以采用了保守的SSH框架，采用Maven构建，数据库为MySql5.5，开发环境是JDK1.7和Eclipse Mars
## 项目技术介绍
----------
### 1 Spring
Spring是目前java开发中最流行的框架之一，它用以管理类，其关键点在于Ioc控制方在和DI依赖注入，是整个项目的容器
### 2 Struts2
struts2是继struts后的巨作，和springmvc一样，是企业开发常常采用的控制层框架之一。struts2和springmvc的不同点在于，它采用了拦截器的形式，对所有符合条件的请求进行拦截，且struts2的controller必须是多例的，因为每一个Action都封装了用户请求数据，如果采用单例模式会造成数据紊乱。并且struts2对路径的拦截是类级别的
，而springmvc对路径的拦截是方法级别的
### 3 Hibernate
hibernate是目前流行的持久层框架，在企业开发中常常使用到，和mybatis地位相当。hibernate的核心是Inverse反转和Cascade级联和懒加载。hibernate和mybatis的不同之处在于，它是一款Full ORM全对象关系映射
的框架，使用它独创的Hql查询和Criteria查询时可以不用写Sql语句，我们只需要面向对象编程，而由hibernate
来生成sql就可以，这使得hibernate在处理多表查询时性能优于 mybatis
### 4 Maven
maven是目前java开发常用的构建工具，它具有依赖注入jar包和构建聚合工程，分模块开发的功能

### 5 Shiro
shiro是一款由apache开源的安全管理框架，主要的组件是Subject,SecureManager和Realms，在本项目中，shiro用于校验用户权限，并且根据授权给予相应的权力，比如超级管理员具有所有权力，而普通管理员不能对任何数据进行修改
### 6 Apache POI
Apache POI提供API给Java程式对Microsoft Office格式档案读和写的功能，在本项目中用以读取区域Excel表格，并对其解析导入数据库中存储，在表格的导出模块中也是使用了POI
### 7 Apache CXF
Apache CXF是一款用于快速发布Web Service和接受Web Service的框架，都是很好的选择
### 8 Quartz
quartz是一个完全由java编写的开源作业调度框架,常常用于完成定时任务调度。Quartz经常会用到cron表达式，可以使用国外网站cronmaker辅助生成cron表达式
### 8 Highchart
highchart是一个用纯JavaScript编写的一个图表库， 能够很简单便捷的在web网站或是web应用程序添加有交互性的图表，并且免费提供给个人学习、个人网站和非商业用途使用，在本项目中主要用于生产区域图表


