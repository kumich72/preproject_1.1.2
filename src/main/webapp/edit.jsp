<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Редактирование
    </title>
</head>

<body>
<h1>
    <%
    User user = (User) request.getAttribute("user");
    if (user != null) {
    %>
    <form action="/edit" method="POST">
        <table border="1">
            <tbody>
            <tr>
                <td>User ID: </td>
                <td><%=user.getId() %></td>
            </tr>
            <tr>
                <td>User Name: </td>
                <td><%=user.getName() %> </td>
            </tr>
            <tr>
                <td>User Email: </td>
                <td><input type="text" id="email" name="email" value="<%=user.getEmail() %>" /></td>
            </tr>
            <tr>
                <td>User Password: </td>
                <td><input type="text" id="password" name="password" value="<%=user.getPassword() %>"/></td>
            </tr>
            </tbody>
        </table>
        <br>
        <input type="hidden" id="id" name ="id" value="<%=user.getId() %>">
        <button type="submit">Edit</button>
    </form>
    <%} else {%>
    Error! This user is null! <br>
    <%}%>
</h1>
</body>
</html>