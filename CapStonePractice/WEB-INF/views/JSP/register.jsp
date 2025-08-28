<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register - WorkNest</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Register</h2>
    <form action="UserController?action=register" method="post">
        <label>Name:</label><br/>
        <input type="text" name="name" required /><br/>
        <label>Email:</label><br/>
        <input type="email" name="email" required /><br/>
        <label>Password:</label><br/>
        <input type="password" name="password" required /><br/>
        <label>Role:</label><br/>
        <select name="role">
            <option value="USER">User</option>
            <option value="ADMIN">Admin</option>
        </select><br/>
        <input type="submit" value="Register" />
    </form>
</div>
</body>
</html>