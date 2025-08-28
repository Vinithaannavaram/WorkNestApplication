<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>

    <!-- ✅ Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- ✅ Optional: Your custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>
<div class="container mt-5">
    <h2 class="text-primary mb-4">Welcome, Admin</h2>

    <!-- Navigation Buttons -->
    <div class="mb-4 d-flex justify-content-between">
        <div>
<a href="${pageContext.request.contextPath}/createTask" class="btn btn-success me-2">Allocate New Task</a>
            <a href="${pageContext.request.contextPath}/adminDashboard?showTasks=true" class="btn btn-info">All Tasks</a>
        </div>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Logout</a>
    </div>

    <!-- Conditionally Render Task Table -->
    <c:if test="${showTasks}">
        <h3 class="mb-3">All Tasks</h3>

        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Title</th>
                    <th>Status</th>
                    <th>Assigned To</th>
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
                        <td>${task.assignedTo.name}</td>
                        <td>${task.dueDate}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/taskView?id=${task.id}" class="btn btn-sm btn-primary">View</a>
                            <a href="${pageContext.request.contextPath}/editTask?id=${task.id}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="${pageContext.request.contextPath}/deleteTask?id=${task.id}" 
                               onclick="return confirm('Are you sure you want to delete this task?');"
                               class="btn btn-sm btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty tasks}">
                    <tr>
                        <td colspan="5" class="text-center">No tasks available</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </c:if>
</div>

<!-- ✅ Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>