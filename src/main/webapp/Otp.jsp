<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>OTP Input field</title>
<style>
body {
	margin: 0;
	padding: 0;
	height: 100vh;
	background: #000000; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #434343, #000000);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #434343, #000000);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.container {
	display: flex;
	flex-flow: column;
	height: 100%;
	align-items: center;
	justify-content: center;
}

.userInput {
	display: flex;
	justify-content: center;
}

input {
	margin: 10px;
	height: 35px;
	width: 150px;
	border: none;
	border-radius: 5px;
	text-align: center;
	font-family: arimo;
	font-size: 1.2rem;
	background: #eef2f3;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
h1 {
	text-align: center;
	font-family: arimo;
	color: honeydew;
}

button {
	width: 150px;
	height: 40px;
	margin: 25px auto 0px auto;
	font-family: arimo;
	font-size: 1.1rem;
	border: none;
	border-radius: 5px;
	letter-spacing: 2px;
	cursor: pointer;
	background: #616161; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #9bc5c3, #616161);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #9bc5c3, #616161);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
</head>
<body>
	<form>
		<div class="container">
			<h1>ENTER OTP</h1>
			<div class="userInput">
				<input type="number" name="userOtp" oninput="limitMaxLength(this,6)">	
			</div>
			<button type="submit">CONFIRM</button>
		</div>
	</form></body>
	<script>
function limitMaxLength(input, maxLength) {
  if (input.value.length > maxLength) {
    input.value = input.value.slice(0, maxLength);
  }
}
</script>
</html>