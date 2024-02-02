package com.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String senderEmail;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendSuccessFeePay(String studentEmail, int feeId) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(studentEmail);
            helper.setSubject("Password Reset Code");
            String contentBuilder = "Dear Student,\n\n" +
                    "Your fee Pay Successfully \n\nfee id : "+feeId;

            helper.setText(contentBuilder);

            javaMailSender.send(message);

            System.out.println("Message Successfully Sent.");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send message to the student : " + e.getMessage());
            return false;
        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("An error occurred: " + ex.getMessage());
            return false;
        }
    }
}
