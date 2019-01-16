# code-generator
Java 后端代码生成器

## 0.01 版<br>
1. generateUtil.java 中实现文件生成
1. application.yml 可配置如下信息
> (1) author（作者名称）<br>
(2) version （版本号）<br>
(3) entityName （实体类名称）<br>
(4) description （关于实体类的描述）<br>
(5) path （生成的文件的存放路径，最后不加 \ 或 /）<br>

<br>

## 0.02版
1. 将基本的增删查改单独抽取出来
2. 连接数据库生成 model、mapper 文件。数据库连接和字段的获取在 DatabaseUtil.java 中完成。
3. application.yml 添加数据库配置
> (1) host（主机）<br>
(2) port（端口）<br>
(3) username（用户名）<br>
(4) password（密码）<br>
(5) catalog（数据库名称）<br>
(6) tableName（实体对应的表名）<br>