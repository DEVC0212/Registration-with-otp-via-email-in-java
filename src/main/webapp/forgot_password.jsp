<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Yinka Enoch Adedokun">
<meta name="description"
	content="Simple Forgot Password Page Using HTML and CSS">
<meta name="keywords" content="forgot password page, basic html and css">
<title>Forgot Password Page - HTML + CSS</title>
<style>
body {
    font-family: Arial, sans-serif;
    line-height: 1.6;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
}

.row {
    text-align: center;
    padding: 2em;
    width: 80%;
    margin: 2em auto;
}

.row h1 {
    font-size: 2.5em;
    margin-bottom: 1em;
}

.row input[type="email"] {
    display: block;
    padding: 0.5em 1em;
    width: 100%;
    margin: 0.5em auto;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.row button {
    display: inline-block;
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.row button:hover {
    background-color: #0056b3;
}

.row .footer h5 {
    margin-top: 1em;
}

.row .footer a {
    color: #007bff;
    text-decoration: none;
}

</style>
</head>
<body>
	<!--#include file="header.jsp"-->
	<form action="ResetPassword" method="post">
		<div class="row">
			<h1>Forgot Password</h1>
			<h4 class="information-text">Enter your registered email to
				reset your password.</h4>
			<div class="form-group">
				<input type="email" name="user_email" id="user_email">
				<p>
					<label for="username">Email</label>
				</p>
				<button type="submit">Reset Password</button>
			</div>
			<div class="footer">
				<h5>
					New here? <a href="register.jsp">Sign Up.</a>
				</h5>
				<h5>
					Already have an account? <a href="login.jsp">Sign In.</a>
				</h5>
			</div>
		</div>
	</form>
	<!--#include file="footer.jsp"-->
</body>
</html>

