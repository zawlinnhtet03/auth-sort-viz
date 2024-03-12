package authsortingvisualizer;

import javax.mail.Session;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Zaw Linn Htet
 */
public class JavaMailUtil {
    
     private static String generatedOTP; 
    
     public static void sendMail(String recipient) throws Exception {

        JOptionPane.showMessageDialog(new JFrame(), "OTP will be sent to your email.");
    
        // Set up mail server properties
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        
        String myAccountEmail = "zawlinnhtet5858@gmail.com";
        String myAccountpassword = "htdn vmii zili drah";
      
        // Set up the authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountpassword);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);
        
        Transport.send(message);
//        JOptionPane.showMessageDialog(new JFrame(), "Successfully sent OTP");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("OTP Verification");
            // message.setText("Hello there");

            // Generate a random 6-digit OTP
            generatedOTP = generateOTP();

            // Set the message body with the OTP
            message.setText("Your OTP is: " + generatedOTP);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getGeneratedOTP() {
       return generatedOTP;
    }
    
    private static String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
        return String.valueOf(otpValue);
    }
}
