<h2 align=center>用技术,让女朋友不生气</h2>
<h3 align=right>
基于SpringBoot的情侣礼物分发平台
</h3>

<div align=center>
<img src="https://img.shields.io/badge/language-java-brightgreen">
<img src="https://img.shields.io/badge/-thyemleaf-red">
<img src="https://img.shields.io/badge/-jQuery-orange">
<img src="https://img.shields.io/badge/-JPA-yellow">
<img src="https://img.shields.io/badge/-SpringBoot-blue">
<img src="https://img.shields.io/badge/-Semantic-green">
</div>

------

> [个人主页](http://quguai.cn) &emsp;|&emsp; [CSDN](https://me.csdn.net/qq_41503660) &emsp;|&emsp; 公众号：TeaUrn

### 简介
&emsp;&emsp;对于有女朋友的人士来说，给女朋友买礼物那是在所难免的，但是没有目标的总是缺少点什么。为了能够将购买礼物更加体系化，完成任务就可以获得礼物，也可以说这是一个奖励的平台。当然也可以增加惩罚措施，比如生气扣10积分，**陟罚臧否**。
[演示地址：http://59.110.241.41](http://59.110.241.41:8090/)（最好电脑端访问）

### 前台
<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/5750f2dba57aa2047d932471ddb4e72.png" width=800px>
</div>
<br>
<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/20200718101714.png" width=300px>
</div>

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/e652468368f6709808cc53b62200313.png" width=700px>
</div>

### 关于我们

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/2020718102308dama.png"  width=800px>
</div>
<br>

### 后台管理

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/1222.png"  width=800px>
</div>
<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/0ce76cbddee86279b829f78080e067e.png"  width=800px>
</div>

>里面还有很多细节界面就不一一列举了，感兴趣的小伙伴可以下载源码运行一下，有问题的话可以私信我。

### 涉及到技术
|内容|框架|
|:---:|---|
|数据库|SpringData JPA|
|前端|Semantic-UI|
|前端框架|thymeleaf|
|前端驱动|jQuery|
|后端|SpringBoot|

&emsp;&emsp;数据库方面采用的 **SpringData JPA**, 当然自己也可以改成其他数据库。前端UI采用的Semantic-UI，样式内置用起来方便多了。

&emsp;&emsp;大致流程是这样的，对方发布礼物要求以及积分，只有当个人积分大于礼物的积分才可以购买，但是满不满足要求，需要对方进行审核。流程图如下。

<div align=center>
<img src="https://markdown-liyang.oss-cn-beijing.aliyuncs.com/WebGift/20200718110141.png"  width=300px>
</div>

&emsp;&emsp;积分获取的方式默认采用的是：**积分是每天凌晨获取一个，周末额外获得一个**。当然自己也可以增加其他获得方式，比如纪念日加成等等。有了目标才更加有动力。也可以体现你有多在意她。这里面也可以相互赠送礼物，女方也可以给男方法发布任务或者礼物。

### 喜欢
源码地址：[Couple](https://github.com/LiYangSir/Couple) 喜欢的话，可以Star一下。
