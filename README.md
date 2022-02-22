截图：

##### 权限申请功能：

用于第一次打开App时候申请存储访问，网络，电量优化（后台）的功能

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929101519962.png" alt="image-20210929101519962" style="zoom: 67%;" />

##### 界面展示

打开页面自动打开外置sd卡的DCIM文件夹内，显示所有任务拍摄的文件夹

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929103147159.png" alt="image-20210929103147159" style="zoom:67%;" />



##### 发送功能

1.自定义选择文件夹发送

可以长按文件夹添加到发送队列，左下角发送队列会更新，任务不会重复添加到任务列表中。

在添加完任务之后可以点击右下角按钮“发送队列中文件”即可实现发送。

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929103303999.png" alt="image-20210929103303999" style="zoom:67%;" />

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929103436698.png" alt="image-20210929103436698" style="zoom:67%;" />



2.将所有任务添加到队列

或者点击右上角小飞机可以一键将所有小飞机添加到任务队列中。

发送队列只能添加文件夹，如果添加图片会弹出操作失败。

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20211005184126325.png" alt="image-20211005184126325" style="zoom:67%;" />

中间按钮可以清空发送队列。

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929104934516.png" alt="image-20210929104934516" style="zoom:67%;" />



3.单独发送单个文件夹

长按文件夹选择直接发送。

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20211005184032855.png" alt="image-20211005184032855" style="zoom:67%;" />



##### 文件浏览

点击文件夹可以进入文件夹

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929103841018.png" alt="image-20210929103841018" style="zoom:67%;" />

点击图片，选择使用相册打开，会调用系统相册打开图片：

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20210929104141202.png" alt="image-20210929104141202" style="zoom:67%;" />

##### 服务器配置

点击即可更改服务器配置，实现了应用配置存储的功能，重启APP后悔保存修改后的信息。

<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20211005183611055.png" alt="image-20211005183611055" style="zoom:67%;" />



<img src="https://gitee.com/karlhan/picgo/raw/master/img/image-20211005183632023.png" alt="image-20211005183632023" style="zoom:67%;" />



##### 未实现功能:

4. 实现发送文件夹的删除（安卓权限这里有问题）
5. 实现已发送文件夹的特殊标识

##### 已经实现的功能:

1. 文件管理器界面
2. 单机文件夹发送文件夹所有内容JPG图片给服务器
3. 实现长按选择菜单功能，发送文件夹，点击发送图片的时候会弹窗
4. 实现点击图片调用相册打开
5. 增加了启动权限检查和权限申请功能
6. 重新设计了发送的协议，由原来的发送文件夹完切换一次保存路径，变为每次发送图片都带着自己的文件夹名称，解决了因为bug出现的有可能多个文件夹接收时存到一个文件夹内的情况
7. 完成应用打包签名
8. 实现在页面中更改服务器地址和端口号
9. 实现将服务器配置本地保存，提供默认服务器端口设置，以及修改保存配置
10. 实现界面显示按照文件夹名从小到大显示

##### 目前已知问题:

2. 读取安卓图片的info经纬度坐标，只在安卓7.1生效。 在sdk30实无法使用



##### 已解决问题:

1. 如果有禁止打开的文件，因为无法访问所以会有空的files，会导致报错。
2. 解决了任务可以重复添加在队列中的问题，使用了NavigableSet代替Queue
3. 如果正在发送文件夹过程中，长按发送另一个文件夹会崩溃 
4. 显示正在发送第几个任务，还没有解决
5. 发送队列可能导致多个相册内的图片，接收时候传入一个文件夹内！！！
6. 解决了正在发送第几个任务显示错误的问题。
7. 如果服务器崩溃，没有返回给客户端info信息，客户端也会崩溃的问题。现在会Toast提醒服务器失联
8. 解决了先手动添加一个任务到发送队列，然后点击添加所有任务到发送队列，造成的发送队列的总任务数不对的问题。
9. 解决了清空任务队列造成的发送队列的总任务数不对的问题。
10. 解决了发送完所有任务之后造成的总任务数不对的问题。