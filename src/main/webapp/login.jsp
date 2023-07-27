<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<style>
/* CSS styles for the login form */
body {
	font-family: Arial, sans-serif;
}

form {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-shadow: 0 0 5px #ccc;
}

form h2 {
	text-align: center;
}

label {
	display: block;
	margin-bottom: 8px;
}

input[type="text"], input[type="password"] {
	width: 95%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #333;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #555;
}

.forgot-password {
	text-align: center;
	margin-top: 10px;
}
</style>
</head>
<body>
	<form action="Login" method="post">
		<h2>Login</h2>
		<label for="username">Username</label> <input type="text"
			id="username" name="username" required>
		 <label for="password">Password</label>
		<input type="password" id="password" name="password" required>

		<input type="submit" value="Login">

		<div class="forgot-password">
			<a href="forgot_password.jsp">Forgot Password?</a>
		</div>
	</form>
</body>
</html>

