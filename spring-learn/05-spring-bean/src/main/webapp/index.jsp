<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.yuki.demo5.scope.Boss" %>
<%@ page import="com.yuki.demo5.scope.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        // 获取工厂
        WebApplicationContext fac =
                WebApplicationContextUtils.getWebApplicationContext(application);

        // 获取Car对象
        Boss boss = fac.getBean(Boss.class);
        Car bossCar1 = boss.getCar();
        Car bossCar2 = boss.getCar();

        Car car = fac.getBean("car",Car.class);
        Car car2 = fac.getBean("car",Car.class);
        System.out.println("---- bosscar1 == bosscar2----");
        System.out.println(bossCar1 == bossCar2);
        System.out.println("---- bosscar1 == car----");
        System.out.println(bossCar1 == car);

    %>

    <h6>car2 bean</h6>
    <span><%= fac.getBean("car2",Car.class)%></span>
</body>
</html>
