package in.dev.backend;

public class Sender {
	public static void main(String[] args) throws Exception {
		String otp = OTPGenerator.generateOTP(6);
		EmailSender es = new EmailSender("devchauhan0212@gmail.com",otp);
		es.send();
	}
}