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
    <title>Title</title>
    <script type="text/javascript">
        function submitForms()
        {
            setTimeout("document.getElementById('form1').submit()",1500);

        }
    </script>
</head>
<body onload="submitForms()">
<form id="form1" action="http://localhost:8080/sendSource" method="post">
    <label>
        <input name="token" type="hidden" value=<%=request.getAttribute("token")%>>
        <input name="LOCAL_SERVICE" type="hidden" value=<%=request.getAttribute("LOCAL_SERVICE")%>>
        <input name="username" type="hidden" value=<%=request.getAttribute("username")%>>
    </label>
    <text>拿着JWT中的Token去后端获取资源中......</text>
</form>
</body>
</html>