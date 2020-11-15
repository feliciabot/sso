<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/15
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>
        .errorMsg{
            color:red;
        }
    </style>
    <%
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
    %>
</head>
<body>
<form method="post" action="http://localhost:8080/Register">
    账号:<label>
    <input type="text" name="username">
</label>
    <br>
    密码:<label>
    <input type="password" name="password">
</label>
<%--    <label>--%>
<%--        <p>来自域：<input type="text" name="LOCAL_SERVICE" value=<%=LOCAL_SERVICE%> readonly></p>--%>
<%--    </label>--%>
    <button type="submit">注册</button>
</form>
<p class="errorMsg">
        <% if(request.getParameter("fail")!=null){ %>
    <p style="color: red">用户名已存在！请重新输入</p>
    <% } %>
</p>
</body>
</html>
