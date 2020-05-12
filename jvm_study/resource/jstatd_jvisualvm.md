**使用Java自带jvisualvm远程监控JVM运行情况**

>####1.远程服务器需要做的准备
>1.cd ${JAVA_HOME}/bin
>
>2.sudo vi jstatd.all.policy
>加入内容如下：<br>
>
>      grant codebase "file:${java.home}/../lib/tools.jar" {
>       permission java.security.AllPermission;
>      }:
>
>3.执行:jstatd -J-Djava.security.policy=jstatd.all.policy &
> 或者jstatd -J-Djava.security.policy=jstatd.all.policy -p 端口号
>
>4.如果远程连接jstatd后，节点加载不出来，可以在jstatd启动时开启日志打印，方便定位问题:
>
>     jstatd -J-Djava.security.policy=jstatd.all.policy -J-Djava.rmi.server.hostname=192.168.101.56 -J-Djava.rmi.server.logCalls=true
>     注意192.168.101.56需要改为自己的ip，-p 9099指定服务端口号，默认是1099
>
>5.jstatd启动后，会开启一个随机端口，通过
>                 
>     sudo netstat -lutnp |grep jstatd 
>     查看对应的端口，然后执行以下命令开放这个端口
>     sudo iptables -I INPUT -p tcp --dport 对应端口 -j ACCEPT
>
>6.此时应该可以进行远程监控了     