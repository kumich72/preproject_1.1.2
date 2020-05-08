<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Список всех пользователей
    </title>
</head>

<body>
<h1>
    <form action="/admin/add" method="GET">
        <button type="submit">Add new user</button>
    </form>
    <table border="1">
        <tbody>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null) {
                for (User user : users) { %>
                <tr>
                    <td><%=user.getId() %></td>
                    <td><%=user.getName() %></td>
                    <td><%=user.getEmail() %></td>
                    <td><%=user.getPassword() %></td>
                    <td><%=user.getRole() %></td>
                    <td><form action="/admin/delete" method="POST">
                        <button type="submit">Delete</button>
                        <input type="hidden" id="id" name="id" value="<%=user.getId()%>">
                    </form></td>
                    <td><form action="/admin/edit" method="GET">
                        <button type="submit">Edit</button>
                        <input type="hidden" id="edit_id"  name="edit_id" value="<%=user.getId()%>">
                    </form></td>
                </tr>
            <% }
            }
        %>
        </tbody>
    </table>
</h1>
</body>
</html>