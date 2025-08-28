<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.model.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Dashboard</title>

    <!-- ✅ Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- ✅ Optional: Your custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Your Tasks</h2>
    </div>

    <div class="card shadow">
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Due Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.title}</td>
                            <td>
                                <span class="badge 
                                    <c:choose>
                                        <c:when test="${task.status == 'Completed'}">bg-success</c:when>
                                        <c:when test="${task.status == 'In Progress'}">bg-warning text-dark</c:when>
                                        <c:otherwise>bg-secondary</c:otherwise>
                                    </c:choose>">
                                    ${task.status}
                                </span>
                            </td>
                            <td>${task.dueDate}</td>
                            <td class="d-flex gap-2 flex-wrap">
                                <a href="${pageContext.request.contextPath}/taskView?id=${task.id}" class="btn btn-info btn-sm">View</a>
                                <a href="${pageContext.request.contextPath}/editUserTask?id=${task.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="${pageContext.request.contextPath}/deleteTask?id=${task.id}" 
                                   onclick="return confirm('Are you sure you want to delete this task?');" 
                                   class="btn btn-danger btn-sm">Delete</a>
                                <a href="${pageContext.request.contextPath}/updateStatus?id=${task.id}&status=Completed" 
                                   class="btn btn-success btn-sm">Mark Completed</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty tasks}">
                        <tr>
                            <td colspan="4" class="text-center">No tasks assigned yet.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="text-end mt-3">
        <a href="${pageContext.request.contextPath}/createTask" class="btn btn-primary">Logout</a>
    </div>
</div>

<!-- ✅ Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>