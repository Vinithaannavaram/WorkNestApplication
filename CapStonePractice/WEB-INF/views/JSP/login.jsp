<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - WorkNest</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="UserController?action=login" method="post">
        <label>Email:</label><br/>
        <input type="text" name="email" required /><br/>
        <label>Password:</label><br/>
        <input type="password" name="password" required /><br/>
        <input type="submit" value="Login" />
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
</div>
</body>
</html>