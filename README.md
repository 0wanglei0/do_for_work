# do_for_work
# 目标：向全栈进发 #
- 后台项目  java mysql MongoDB
- 微信小程序 支付宝小程序
- web angularjs vuejs nodejs
- app Android ios

通过一个简单的hello world小项目，实现前后端交互。

- spring boot 的后台
- vuejs的html页面
- android app 
- ios app
- 微信小程序

## 记录所做及问题 ##
### 2021.10.02

1. spring boot demo项目创建
1. [项目创建](https://start.spring.io/)  
	- 填写项目信息后下载即可，会导出一个demo项目
1. 修改java环境变量 完成
1. `gradle bootRun`
1. 需要gradle 升级，spring boot 2.5.5最低要gradle 6.7
1. ide下载  完成vs code IntelJ并时间有效
1. [快速开始](https://spring.io/quickstart) 完成

**需完成：
运行代码没有看到网络请求和页面代码
WEB SERVER FAILED TO START. PORT 8080 WAS ALREADY IN USE.**


### 2021.10.06

1. 设置spring boot 端口
	- [设置spring boot 端口](https://blog.csdn.net/weixin_38569499/article/details/86644990)
	- 配置文件入口在resources里,通过配置文件可解决
1. 启动应该是成功了，但是Whitelabel Error Page
	- [解决方案1](https://www.cnblogs.com/williamjie/p/9199586.html)
	- [解决方案2](https://blog.csdn.net/lh87270202/article/details/79925951)
	- 以上解决了问题，没有默认的HTML页面
1. vuejs 前后端分离开发进行了解
	- [查看资料](https://www.cnblogs.com/javazhiyin/p/11237966.html)
1. 百度网盘下载慢的解决
	- [忽快忽慢，效果一般，总比没有强](https://www.bilibili.com/read/cv7775560/)

### 2021.10.08
1. 配置vuejs的前端环境  
	1.1 安装nodejs  
	下载地址：[nodejs](https://nodejs.org/zh-cn/download/)  
	选择了安装程序，下载完成直接安装，直接下一步到完成，自动配置了环境变量  
	1.2 安装@vue/cli  
    1. npm install -g @vue/cli  
	2. [官网中有详细说明](https://cli.vuejs.org/zh/guide/installation.html)  
	3. 安装nodejs时修改了安装路径，配置了新的nodejs的缓存和prefix，安装vuejs时提示权限不足，修改了文件夹属性  
	4. 官网里安装vuejs/cli也可以使用yarn安装，安装yarn时提示文件权限不足，管理员模式运行cmd，进入powershell，运行choco install yarn 安装即可  
	5. 安装成功之后需要配置vuejs的环境变量  

	1.3 命令行创建vue项目  
		`vue create project_name`  
		会出现选择题，通过方向键选择，点击后没有UI变化，但实际已经选择了，所以要控制好点击次数，按回车就可以了，可以创建同名项目，会提示覆盖或者合并或取消，也可以通过vue ui的方式创建、导入项目  
 		
	1.4 运行创建好的项目  
 		`cd project_path`  
     	`yarn serve`   
 		浏览器打开localhost:8080即可打开创建的demo项目

2. github上传项目
	- github创建新仓库
	- clone github项目到本地
	- git遇到fatal: unable to access ‘https://github.com/xxx/xxx/’:OpenSSL SSL_read: Connection was aborted, errno 10053的问题  
	解决办法：git config --global http.postBuffer 524288000,不一定有效  
	或者修改window的host文件，路径是c:\\windows\system32\drivers\etc\hosts
	- 无法上传 需要解决 source tree 或者命令行,可能只是网络的问题，时好时坏

3. Markdown程序使用，需要额外安装 Awesomium 1.6.6 SDK才能正常使用预览  
[下载路径](http://markdownpad.com/download/awesomium_v1.6.6_sdk_win.exe) ，正常安装即可


### 2021.10.18
- 打算看看怎么创建spring boot 的controller，还有创建vuejs的网络请求，然后怎么前后端分离请求  
  1. 创建spring boot的Hello world的controller  
  1.1 @Controller 整个页面刷新使用，@RestController局部刷新使用，一般返回json格式  
  1.2 controller的注解使用在类中，不能使用在方法，如果返回字符串需要添加@Response注解，否则默认应该返回view
- 创建显示Hello world的页面  
  下载了webStorm

- 请求实现  

- 前后端分离需要nginx  
  https://www.cnblogs.com/knowledgesea/p/5175711.html  
  没太看懂

- 有点不知道怎么下手了