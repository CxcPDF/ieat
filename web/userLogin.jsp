<%--
  Created by IntelliJ IDEA.
  User: 小巷有狗
  Date: 2018/3/24
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录界面</title>
</head>
<body>
<%
    request.setCharacterEncoding("GB2312");
    response.setContentType("text/html;charset=gb2312");
%>
<form action="/servlet/user/ParseJSONServlet" method="post">
    ID:<input type="text" name="userName"><br>
    PSD:<input type="text" name="userPassword"><br>
    登录方式：<<input type="text" name="login_type"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
