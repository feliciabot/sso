<%--
  Created by IntelliJ IDEA.
  User: RANFEISHA
  Date: 2020/11/12
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style>
        .errorMsg{
            color:red;
        }
    </style>
</head>
<body>
<h2 align="center">登录页面</h2>
<div align="center">
<form method="post" action="http://localhost:8080/ServerSendJWT">
    账号:<label>
    <input type="text" name="username">
</label>
    <br>
    密码:<label>
    <input type="password" name="password">
</label>
    <label>
        <p>来自域：<input type="text" name="LOCAL_SERVICE" value="http://localhost:8083/" readonly></p>
    </label>
    <button type="submit">点击登录</button>
</form>
    <a href="http://localhost:8080/Register">注册</a>
<p class="errorMsg">
    <% if(request.getParameter("fail")!=null){ %>
    <p style="color: red">用户名或密码输入错误，请重新输入！！！</p>
    <% } %>
</p>
</div>
</body>
</html>