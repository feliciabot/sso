<%--
  Created by IntelliJ IDEA.
  User: RANFEISHA
  Date: 2020/11/12
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>主页</title>
    <%
        String token=request.getParameter("token");
        String LOCAL_SERVICE=request.getParameter("LOCAL_SERVICE");
        Cookie[] cookies=request.getCookies();
        if(cookies!=null && token==null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    token=cookie.getValue();
                    LOCAL_SERVICE="http://localhost:8081/";
                }
            }
        }
    %>
</head>
<body>
    <h2 align="center">子系统1</h2>
    <div align="center">
        <form id="form1" action="http://localhost:8080/sendSource" method="post">
            <input name="token" type="hidden" value=<%=token%>>
            <input name="LOCAL_SERVICE" type="hidden" value=<%=LOCAL_SERVICE%>>
            <input type="submit" value="查看用户信息">
        </form>
        <a href="/logout">退出登录</a><br/>
    </div>
</body>
</html>