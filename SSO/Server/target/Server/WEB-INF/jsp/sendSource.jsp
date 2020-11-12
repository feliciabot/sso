<%--
  Created by IntelliJ IDEA.
  User: RANFEISHA
  Date: 2020/11/12
  Time: 14:20
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
<%
    String JWT=(String) request.getAttribute("JWT");
    String LOCAL_SERVICE=(String) request.getAttribute("LOCAL_SERVICE");
    String username=(String) request.getAttribute("username");
    String uri=LOCAL_SERVICE+"GetSource";
%>
<form id="form1" action=<%=uri%> method="post">
    <label>
        <input name="BooksJson" type="hidden" value=<%=request.getAttribute("BooksJson")%>>
        <input name="username" type="hidden" value=<%=request.getAttribute("username")%>>
        <input name="LOCAL_SERVICE" type="hidden" value=<%=request.getAttribute("LOCAL_SERVICE")%>>
    </label>
    <text>发送JWT中......</text>
</form>
</body>
</html>