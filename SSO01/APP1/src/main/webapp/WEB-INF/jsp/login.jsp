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
</head>
<body>
<form method="post" action="http://localhost:8080/ServerSendJWT">
    账号:<label>
    <input type="text" name="username">
</label>
    <br>
    密码:<label>
    <input type="password" name="password">
</label>
    <label>
        <p>来自域：<input type="text" name="LOCAL_SERVICE" value="http://localhost:8081/" readonly></p>
    </label>
    <button type="submit">点击登录</button>
</form>
</body>
</html>