<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.model.Task" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Welcome, Admin</h2>
    <a href="taskForm.jsp">Allocate New Task</a> |
    <a href="UserController?action=logout">Logout</a>

    <h3>All Tasks</h3>
    <table border="1">
        <tr>
            <th>Title</th><th>Status</th><th>Assigned To</th><th>Due Date</th><th>Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.title}</td>
                <td>${task.status}</td>
                <td>${task.assignedTo.name}</td>
                <td>${task.dueDate}</td>
                <td>
                    <a href="TaskController?action=view&id=${task.id}">View</a> |
                    <a href="TaskController?action=edit&id=${task.id}">Edit</a> |
                    <a href="javascript:confirmDelete('TaskController', ${task.id})">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="script.js"></script>
</body>
</html>