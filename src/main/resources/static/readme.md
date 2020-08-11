
Sortable.js 拖放排序插件 使用说明：

https://blog.csdn.net/kangkang_style/article/details/88825907

sortable.js 拖拽排序及配置项说明
https://www.cnblogs.com/lizhao123/p/10740783.html


semantic文档地址： https://zijieke.com/semantic-ui/usage/theming.php

semantic网上的例子参考： https://blog.csdn.net/qq_46101869/article/details/107582966


jq实现星星评价
https://www.jianshu.com/p/02969846f1ed


thymeleaf模板传值
https://blog.csdn.net/zhengun/article/details/84918578

Spring Boot集成thymeleaf异步刷新页面
https://blog.csdn.net/aliasone/article/details/82261688


thymeleaf+semantic ui分页
https://my.oschina.net/ayyao/blog/898041


参考：https://www.cnblogs.com/pogusanqian/p/12768876.html
<script type="text/javascript" th:inline="javascript">
 
    //协议名称
    var scheme = [[${#request.getScheme()}]];
 
    //服务器名称
    var serverName = [[${#request.getServerName()}]];
 
    //服务器端口号
    var serverPort = [[${#request.getServerPort()}]];
 
    //上下文根
    var contextPath = [[${#request.getContextPath()}]];
 
    //获取请求参数
    var queryString = [[${#request.queryString}]];
 
    //获取请求路径(不带参数)
    var requestURL = [[${#request.requestURL}]];
 
    alert(queryString);
 
</script>


thymeleaf中相对路径的两种方式
https://www.cnblogs.com/wldbk/p/12201233.html

<img src="../static/images/1.png" th:src="@{/images/1.png}"/><!--http://localhost:9090/learn-thymeleaf116/images/1.png-->
<img src="../static/images/1.png" th:src="@{images/1.png}"/><!--http://localhost:9090/learn-thymeleaf116/I18n/aaa/images/1.png-->

这里主要images前面带/和不带/的区别：

前面加"/"：访问的路径是从服务器的根路径而言的，就是application.yml里面配置的context-path，上我我配置的是/learn-thymeleaf116，所以访问路径为http://localhost:9090/learn-thymeleaf116/images/1.png。

前面不加"/"：访问路径是相对于当前的路径而言的，比如上面的第二个，这个请求的的路径为http://localhost:9090/learn-thymeleaf116/I18n/aaa/use，相对于他的当前路径就是去掉use，所以最终的访问路径就是http://localhost:9090/learn-thymeleaf116/I18n/aaa/images/1.png




