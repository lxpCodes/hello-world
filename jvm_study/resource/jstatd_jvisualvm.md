**使用Java自带jvisualvm远程监控JVM运行情况**

>####1.使用jstatd
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
>
>
>
>>####2.使用jmx
>1.远程服务器准备
>           
>      cd ${JAVA_HOME}/
>      cd jre/
>      cd lib/
>      cd management/
>      cp jmxremote.password.template jmxremote.password
>      sudo vi jmxremote.password
>      文件中将monitorRole  QED和 controlRole   R&D前的注释去掉，然后保存 
>
>2.更改java参数
>    
>     cd /etc/supervisord.d/  
>     在java启动项里加上
>     -Djava.rmi.server.hostname=【远程服务器ip】 -Dcom.sun.management.jmxremote.port=【指定端口】 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
>
>3.重启supervisor
>   
>     sudo supervisor reload



        