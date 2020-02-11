<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.yuki.demo5.anno.LoginController" %>
<%@ page import="org.springframework.web.context.support.XmlWebApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 全局变量fac
    XmlWebApplicationContext fac =
            (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(application);
    // 刷新配置
    fac.refresh();
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
  <%= fac.getBean(LoginController.class).getLoginService() == null%>
  <%= fac.getBean(LoginController.class)%>
</body>
</html>
