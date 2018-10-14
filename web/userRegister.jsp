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
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
%>
<form action="/servlet/user/ParseJSONServlet" method="post">
    <%--name:<input type="text" name="userName"><br>--%>
    <%--psd:<input type="text" name="userPassword"><br>--%>
    <%--注册方式:<<input type="text" name="register_type"><br>--%>

        <input type="text" name="111"><br>
    <input type="submit">
</form>
</body>
</html>
