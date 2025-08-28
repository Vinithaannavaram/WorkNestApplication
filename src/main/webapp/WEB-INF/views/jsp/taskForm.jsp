<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Task Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card shadow p-4">
        <h3 class="text-center text-primary mb-4">
            <c:choose>
                <c:when test="${mode == 'editUser'}">Edit Task</c:when>
                <c:when test="${mode == 'view'}">Task Details</c:when>
                <c:otherwise>Create Task</c:otherwise>
            </c:choose>
        </h3>

        <c:choose>
            <c:when test="${mode == 'view'}">
                <!-- VIEW MODE -->
               <!-- Task Details -->
<p><strong>Title:</strong> ${task.title}</p>
<p><strong>Description:</strong> ${task.description}</p>
<p><strong>Start Date:</strong> ${task.startDate}</p>
<p><strong>Due Date:</strong> ${task.dueDate}</p>
<p><strong>Status:</strong> ${task.status}</p>

<!-- Comments Section -->
<hr class="my-4">
<h5 class="text-primary">Comments</h5>

<c:if test="${not empty comments}">
    <ul class="list-group mb-4">
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <div class="d-flex justify-content-between">
                    <strong>${comment.authorName}</strong>
                    <small class="text-muted">${comment.timestamp}</small>
                </div>
                <p class="mb-0"><c:out value="${comment.text}" /></p>
            </li>
        </c:forEach>
    </ul>
</c:if>

<!-- Add Comment Form -->
<form action="${pageContext.request.contextPath}/addComment" method="post">
    <input type="hidden" name="taskId" value="${task.id}" />
    <div class="mb-3">
        <label for="commentText" class="form-label">Add a Comment</label>
        <textarea name="commentText" id="commentText" class="form-control" rows="3" placeholder="Write your thoughts..." required></textarea>
    </div>
    <div class="d-grid">
        <button type="submit" class="btn btn-primary">Post Comment</button>
    </div>
</form>

                <hr class="my-4">

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/editTask?id=${task.id}" class="btn btn-warning">Edit Task</a>
                    <a href="${pageContext.request.contextPath}/deleteTask?id=${task.id}" onclick="return confirm('Are you sure you want to delete this task?');" class="btn btn-danger">Delete Task</a>
                    <a href="${pageContext.request.contextPath}/${sessionScope.currentUser.role == 'ADMIN' ? 'adminDashboard' : 'userDashboard'}" class="btn btn-secondary">Back to Dashboard</a>
                </div>
            </c:when>

            <c:otherwise>
               <!-- CREATE or EDIT MODE -->
                <form action="${pageContext.request.contextPath}/${mode == 'edit' ? (sessionScope.currentUser.role == 'ADMIN' ? 'updateTask' : 'updateUserTask') : 'saveTask'}" method="post">
                    <div class="mb-3">
                        <label class="form-label">Title</label>
                        <c:choose>
                            <c:when test="${sessionScope.currentUser.role != 'ADMIN'}">
                                <input type="text" name="title" value="${task.title}" class="form-control" required readonly />
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="title" value="${task.title}" class="form-control" required />
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea name="description" class="form-control" required>${task.description}</textarea>
                    </div>

                    <c:if test="${sessionScope.currentUser.role == 'ADMIN'}">

                        <div class="mb-3">
                            <label class="form-label">Start Date</label>
                            <input type="date" name="startDate" value="${task.startDate}" class="form-control" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Due Date</label>
                            <input type="date" name="dueDate" value="${task.dueDate}" class="form-control" />
                        </div>
                        <h5 class="text-primary mt-4">Add a Comment</h5>
    <input type="hidden" name="taskId" value="${task.id}" />

    <div class="mb-3">
        <textarea name="commentText" class="form-control" rows="3" placeholder="Write your comment..." required></textarea>
    </div>

    <div class="d-grid">
        <button type="submit" class="btn btn-primary">Post Comment</button>
    </div>
</form>
                        <c:if test="${not empty userList}">
                            <div class="mb-3">
                                <label class="form-label">Assign To</label>
                                <select name="assignedToId" class="form-select" required>
                                    <c:forEach var="user" items="${userList}">
                                        <option value="${user.id}" ${user.id == task.assignedTo.id ? 'selected' : ''}>
                                            ${user.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </c:if>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">
                            ${mode == 'edit' ? 'Update Task' : 'Create Task'}
                        </button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<c:if test="${mode == 'create'}">
    <form action="${pageContext.request.contextPath}/saveTask" method="post">
        <!-- Form fields here -->
    </form>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>