package in.dev.backend;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OtpServlet")
public class OtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String enteredOtp = req.getParameter("userOtp");
		String hashedOtp = MD5.getMD5EncryptedValue(enteredOtp);
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		String otp = (String)session.getAttribute("Otp");
		String timeStamp = (String)session.getAttribute("TimeStamp");
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentTimeStamp = currentTime.format(formatter);

		if(hashedOtp.equals(otp) && isValidTimestamp(currentTimeStamp,timeStamp,1)) {
			String name = (String)session.getAttribute("username");	
			String password = (String)session.getAttribute("password");	
			String hashedPassword = MD5.getMD5EncryptedValue(password);
			storeUserDataInDatabase(name,email,hashedPassword);
			
			session.removeAttribute("Otp");
			session.removeAttribute("TimeStamp");
            res.sendRedirect("login.jsp");
		}
		else {
			out.println("<html><body><h2>Invalid OTP. Please try again!</h2></body></html>");
		}
	}	
	private boolean isValidTimestamp(String currentTimestamp, String otpTimestamp, int expiryMinutes) {
	    // Parse timestamps and calculate the time difference in minutes
	    LocalDateTime currentDateTime = LocalDateTime.parse(currentTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    LocalDateTime otpDateTime = LocalDateTime.parse(otpTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    long timeDifference = java.time.Duration.between(otpDateTime, currentDateTime).toMinutes();
	    
	    // Check if the time difference is within the expiry limit
	    return timeDifference <= expiryMinutes;
	}
	private void storeUserDataInDatabase(String name, String email, String password) {
		 	String jdbcURL = "jdbc:mysql://localhost:3306/employee";
	        String dbUser = "root";
	        String dbPassword = "";

	        try {
	             //Load the MySQL JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	             //Create a connection to the database
	            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

	             //Insert data into the table_user
	            String insertQuery = "INSERT INTO employee_table (UserName, Email, Password) VALUES (?, ?, ?)";
	            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	            preparedStatement.setString(1, name);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, password);
	            preparedStatement.executeUpdate();
	            preparedStatement.close();

	            // Remove the OTP entry from the table_user_temp as it is no longer needed
	            String deleteQuery = "DELETE FROM otp_table WHERE Email=?";
	            PreparedStatement deletePreparedStatement = conn.prepareStatement(deleteQuery);
	            deletePreparedStatement.setString(1, email);
	            deletePreparedStatement.executeUpdate();
	            deletePreparedStatement.close();

	            conn.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            // Handle database errors
	        }
	}
}
