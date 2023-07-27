package in.dev.backend;


import java.util.Random;
public class OTPGenerator {
	
	public static String generateOTP(int length) {
		String allowedCharacters = "0123456789";
		
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedCharacters.length());
            otp.append(allowedCharacters.charAt(index));
        }
        
        return otp.toString();
	}
}

