<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Task (Admin)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-primary mb-4">Create Task (Admin)</h2>
    <form action="${pageContext.request.contextPath}/admin/saveTask" method="post">
        <input type="text" name="title" class="form-control mb-3" placeholder="Title" required>
        <textarea name="description" class="form-control mb-3" placeholder="Description" required></textarea>
        <input type="date" name="startDate" class="form-control mb-3" required>
        <input type="date" name="dueDate" class="form-control mb-3" required>
        <select name="assignedTo" class="form-control mb-3">
            <c:forEach var="user" items="${userList}">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>
        <textarea name="comment" class="form-control mb-3" placeholder="Add a comment"></textarea>
        <button type="submit" class="btn btn-primary">Create Task</button>
    </form>
</div>
</body>
</html>