<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Task</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Create Task</h2>
    <form action="TaskController?action=create" method="post">
        <label>Title:</label><br/>
        <input type="text" name="title" required /><br/>
        <label>Description:</label><br/>
        <textarea name="description"></textarea><br/>
        <label>Start Date:</label><br/>
        <input type="date" name="startDate" /><br/>
        <label>Due Date:</label><br/>
        <input type="date" name="dueDate" /><br/>
        <label>Assign To (User ID):</label><br/>
        <input type="number" name="assignedTo" /><br/>
        <input type="submit" value="Create Task" />
    </form>
</div>
</body>
</html>