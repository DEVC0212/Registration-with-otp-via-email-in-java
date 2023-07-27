package in.dev.backend;

import java.io.IOException;

//import org.mindrot.jbcrypt.BCrypt;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.mindrot.jbcrypt.BCrypt;

//import com.mysql.cj.xdevapi.Statement;

@WebServlet("/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final String URL = "jdbc:mysql://localhost:3306/employee";
	final String UserName = "root";
	final String Password = "";

	Connection conn = null;
	Statement s = null;

	public Login() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String hashedPassword = MD5.getMD5EncryptedValue(password);
		
		try {
			conn = DriverManager.getConnection(URL, UserName, Password );
			String query = "SELECT * FROM employee_table WHERE UserName='"+username+"' and Password = '"+hashedPassword+"'" ;
			s=conn.createStatement();
			ResultSet result = s.executeQuery(query);
			if(result.next()){
//				out.println("SuccessfulL");
				res.sendRedirect("Home_LoggedIn.jsp");
			}
			else {
//				out.println("Invalid Credentials");
				res.sendRedirect("Home.jsp");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print(e);
		}
	}
}
