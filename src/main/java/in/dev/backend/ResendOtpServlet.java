package in.dev.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ResendOtpServlet")
public class ResendOtpServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Implement the doGet method to handle GET requests for OTP resend
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Generate a new OTP and send it to the user's email
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String newOtp = OTPGenerator.generateOTP(6);
        session.setAttribute("Otp", MD5.getMD5EncryptedValue(newOtp));

        // Send the new OTP to the user's email using the EmailSender class (similar to the registration process)
        EmailSender emailSender = new EmailSender(email, newOtp);
        try {
            emailSender.send();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return a response indicating that the OTP has been resent
        res.setContentType("text/plain");
        res.getWriter().write("New OTP has been sent to your email.");
    }
}

