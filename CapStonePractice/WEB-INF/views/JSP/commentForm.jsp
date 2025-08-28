<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Comment</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Add Comment</h2>
    <form action="CommentController?action=add" method="post">
        <input type="hidden" name="taskId" value="${task.id}" />
        <label>Your Comment:</label><br/>
        <textarea name="content" required></textarea><br/>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>