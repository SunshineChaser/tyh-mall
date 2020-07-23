# tyh电商项目

------
项目中所涉及到的技术栈：

> * SSM框架
> * Redis
> * activeMQ
> * fastdfs分布式文件系统
> * nginx
> * dubbo
> * freemaker
> * zookeeper

## 1.首页

- 使用Redis作为首页缓存，从Redis中读取数据，发送json数据到前端；
- 设置activeMQ消息队列监听器，接受更新缓存的消息。

![首页未登录图片](http://120.76.137.138/GitHub-tyh-readme-images/home(unlogin).png)

## 2.商品详情

- 使用freemaker作为页面静态化工具

![商品详情图片](http://120.76.137.138/GitHub-tyh-readme-images/details.png)

## 3.后台管理
### 3.1商品类型管理
- SSM框架实现对于商品的增删改查

![商品类型管理图片](http://120.76.137.138/GitHub-tyh-readme-images/productTypeManage.png)

### 3.2商品管理
- SSM框架实现对于商品的增删改查
- 使用fastdfs分布式文件系统+nginx作为商品图片的管理
- CKeditor+fastdfs，增加商品时的富文本编辑器部分

### 3.3发送更新消息
- 当商品或者商品类型被更新时（包括增加，修改，删除），通过activeMQ发送更新消息

## 4.登录注册
- 注册
- SSO单点登录
- 登录状态信息存储在Redis中，前端通过读取cookie发送登录验证

## 5.其他
- dubbo服务
- zookeeper作为注册中心
- 使用maven进行管理
- 本地使用Tomcat插件
- 服务器上使用Tomcat9
