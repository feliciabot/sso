<%@ page import="net.minidev.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: RANFEISHA
  Date: 2020/11/12
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function submitForms()
        {
            setTimeout("document.getElementById('form1').submit()",500);

        }
    </script>
</head>
<body onload="submitForms()">
<%

    String token =(String) request.getAttribute("token");
    String LOCAL_SERVICE=(String) request.getAttribute("LOCAL_SERVICE");
    String username=(String) request.getAttribute("username");
    String uri=LOCAL_SERVICE+"GetJWT";

%>
<form id="form1" action=<%=uri%> method="post">
    <label>
        <input name="token" type="hidden" value=<%=request.getAttribute("token")%>>
        <input name="LOCAL_SERVICE" type="hidden" value=<%=request.getAttribute("LOCAL_SERVICE")%>>
        <input name="username" type="hidden" value=<%=request.getAttribute("username")%>>
    </label>
    <text>发送JWT中......</text>
</form>
</body>
</html>
