<%--
  Created by IntelliJ IDEA.
  User: ruy
  Date: 5/29/22
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <% for(int i=0;i<list.size();i++){ %>
    <li><%=list.get(i)%></li>
    <% } %>
</ul>
</body>
</html>
