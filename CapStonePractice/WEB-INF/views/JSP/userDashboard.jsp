<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.model.Task" %>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Your Tasks</h2>
    <a href="UserController?action=logout">Logout</a>
    <table border="1">
        <tr>
            <th>Title</th><th>Status</th><th>Due Date</th><th>Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.title}</td>
                <td>${task.status}</td>
                <td>${task.dueDate}</td>
                <td>
                    <a href="TaskController?action=view&id=${task.id}">View</a> |
                    <a href="TaskController?action=updateStatus&id=${task.id}&status=Completed">Mark Completed</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>