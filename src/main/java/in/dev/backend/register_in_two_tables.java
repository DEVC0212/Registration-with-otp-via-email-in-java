package in.dev.backend;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Register")
public class register_in_two_tables extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        HttpSession session = req.getSession();
        String otp = (String) session.getAttribute("Otp");
        String timeStamp = (String) session.getAttribute("TimeStamp");
        
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTimeStamp = currentTime.format(formatter);

        if (otp == null || isExpired(currentTimeStamp, timeStamp, 1)) {
            otp = OTPGenerator.generateOTP(6);
            String hashedOtp = MD5.getMD5EncryptedValue(otp);
            timeStamp = currentTime.format(formatter);
            session.setAttribute("Otp", hashedOtp);
            session.setAttribute("TimeStamp", timeStamp);
            EmailSender es = new EmailSender(email, otp);
            try {
                es.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("username", name);
        session.setAttribute("email", email);
        session.setAttribute("password", password);
        res.sendRedirect("otp_prac.jsp");

    }

    private boolean isExpired(String currentTimeStamp, String otpTimestamp, int expiryMinutes) {
        LocalDateTime currentTime = LocalDateTime.parse(currentTimeStamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime otpTime = LocalDateTime.parse(otpTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long timeDifference = java.time.Duration.between(otpTime, currentTime).toMinutes();
        return timeDifference > expiryMinutes;
    }
}
