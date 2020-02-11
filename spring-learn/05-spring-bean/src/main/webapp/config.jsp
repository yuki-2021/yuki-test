<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.yuki.demo5.anno.LoginController" %>
<%@ page import="org.springframework.web.context.support.XmlWebApplicationContext" %>
<%@ page import="com.yuki.demo5.anno.UserController" %>
<%@ page import="com.yuki.demo5.scope.Boss" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 全局变量fac
    XmlWebApplicationContext fac =
            (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(application);
    // 刷新配置
    fac.refresh();

    // 注入boss
    Boss boss = fac.getBean("boss", Boss.class);
%>

<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
  <%= fac.getBean(UserController.class)%>

  <hr>

  <%= boss%>
</body>
</html>
