<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>OTP Verification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 100px;
        }

        input[type="number"] {
            padding: 10px;
            font-size: 16px;
            text-align: center;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <form action="OtpServlet" method="post">
        <h1>OTP Verification</h1>
        <p>Please enter the OTP sent to your email:</p>
        <input type="number" id="otpInput" name="userOtp" oninput="limitMaxLength(this, 6)">
        <br><br>
        <button type="submit">Verify OTP</button>
        <br><br>
        <p id="time"></p>
        <button class="resend-btn" type="button" id="resendBtn" onclick="resendOtp()" disabled>Resend OTP</button>
        <p id="waitMessage" style="display: none;">Please wait for a few seconds...</p>
        <br><br>
        <p id="resend"></p>
    </form>

    <script>
        function limitMaxLength(input, maxLength) {
            if (input.value.length > maxLength) {
                input.value = input.value.slice(0, maxLength);
            }
        }

        function displayCountdown(countdownTime, callback) {
            var interval = setInterval(function() {
                var currentTime = new Date().getTime();
                var timeDifference = countdownTime - currentTime;

                // Calculate the minutes and seconds
                var minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

                // Add leading zero to single-digit numbers
                minutes = (minutes < 10 ? "0" : "") + minutes;
                seconds = (seconds < 10 ? "0" : "") + seconds;

                var formattedTime = minutes + ":" + seconds;
                document.getElementById("time").innerHTML = formattedTime;

                // Stop the countdown when time reaches zero
                if (timeDifference < 0) {
                    clearInterval(interval);
                    document.getElementById("time").innerHTML = "Time expired";
                    document.getElementById("otpInput").disabled = true;
                    document.getElementById("resendBtn").disabled = false;
                    document.getElementById("resend").innerHTML = "OTP has expired. Click 'Resend OTP' to get a new one.";
                    if (typeof callback === "function") {
                        callback();
                    }
                }
            }, 1000);
        }

        // Calculate the OTP expiration time (e.g., 5 minutes from now)
        var currentTime = new Date().getTime();
        var expirationTime = currentTime + (1 * 60 * 1000); // 1 minute in milliseconds

        // Call the function to start the countdown
        displayCountdown(expirationTime, countdownCompleted);

        var waitingTime = 30; // Wait time in seconds
        var isResendEnabled = true; // Add this variable to keep track of the resend button status

        function resendOtp() {
            if (isResendEnabled) {
                // Disable the resend button to prevent multiple requests
                isResendEnabled = false;
                document.getElementById("resendBtn").disabled = true;
                document.getElementById("waitMessage").style.display = "block"; // Show wait message

                setTimeout(function() {
                    // After waiting for the specified time, enable the resend button again
                    isResendEnabled = true;
                    document.getElementById("resendBtn").disabled = false;
                    document.getElementById("waitMessage").style.display = "none"; // Hide wait message
                }, waitingTime * 1000); // Convert waitingTime to milliseconds

                // Make an AJAX request to your server to trigger OTP generation and sending
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "ResendOtpServlet", true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        alert("New OTP has been sent to your email.");
                        document.getElementById("otpInput").disabled = false; // Enable the input field after resend
                        document.getElementById("resend").innerHTML = "";
                        // Calculate the new OTP expiration time and restart the countdown
                        var currentTime = new Date().getTime();
                        var expirationTime = currentTime + (1 * 60 * 1000); // 1 minute in milliseconds
                        displayCountdown(expirationTime, countdownCompleted);
                        // Hide the wait message after OTP is sent
                        document.getElementById("waitMessage").style.display = "none";
                    }
                };
                xhr.send();
            }
        }


        function countdownCompleted() {
            document.getElementById("otpInput").disabled = true;
            document.getElementById("resendBtn").disabled = false;
            document.getElementById("resend").innerHTML = "OTP has expired. Click 'Resend OTP' to get a new one.";
        }
    </script>
</body>

</html>
