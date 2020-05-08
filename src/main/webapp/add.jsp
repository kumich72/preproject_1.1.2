<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Add new User!
    </title>
</head>

<body>
<h1>
    You can add new user!
    <form action="/admin/add" method="POST">
        <table>
            <tbody>
            <tr>
                <td>User Name:</td>
                <td><input type="text" id="name" name="name"/></td>
            </tr>
            <tr>
                <td>User Email</td>
                <td><input type="text" id="email" name="email"/></td>
            </tr>
            <tr>
                <td>User Password</td>
                <td><input type="text" id="password" name="password"/></td>
            </tr>
            <tr>
                <td>User Role</td>
                <td>
                    <select name="role">
                        <option selected value="user">user</option>
                        <option value="admin">admin</option>
                    </select></td>
            </tr>
            </tbody>
        </table>
        <br>
        <br>
        <br>
        <button type="submit">Add new User</button>
    </form>
</h1>
</body>
</html>