package com.school.LoginService.Service;

import com.school.LoginService.FeignService.StaffServiceFeign;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Model.Otp;
import com.school.LoginService.Repo.LoginRepo;
import com.school.LoginService.Repo.OtpRepo;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;


@Component
public class UtilitiesService {


    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private JwtService jwtService;


    @Autowired
    private StaffServiceFeign staffServiceFeign;


    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private LoginRepo loginRepo;



    public boolean sendEmail(String toMailId, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(toMailId);
            helper.setSubject(subject);
            helper.setText(body);

            configureMailSender();

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void configureMailSender() {
        if(javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) javaMailSender;

            Properties mailProperties = new Properties();
            mailProperties.setProperty("mail.smtp.starttls.enable", "true");
            mailProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com"); // Replace with your mail server hostname

            mailSenderImpl.setJavaMailProperties(mailProperties);
        }
    }



    public String getOtpSetUser(String email, String franchiseId , String subject, String body, int otp, String roleType, String uniqueId){


        try{
            List<Otp> old = otpRepo.findAllByEmail(email);
            System.out.println("test this because it can casue an error");
            otpRepo.deleteAll(old);
            System.out.println("old OTP deleted");
        }catch (Exception e){
            System.out.println("not found in OTP Table");
        }

        LocalDateTime issued = LocalDateTime.now();
        LocalDateTime expiry = issued.plusSeconds(600);

        Otp otpSent = new Otp();

        otpSent.setOtp(otp);
        otpSent.setEmail(email);
        otpSent.setIssue(issued);
        otpSent.setExpires(expiry);

        boolean sent = sendEmail(email, subject, body);

        if(sent){
            otpRepo.save(otpSent);
            LoginModel user = loginRepo.getReferenceByemail(email);
            if(user == null){
                LoginModel newuser = new LoginModel();
                System.out.println(franchiseId);
                newuser.setSchoolId(franchiseId);
                newuser.setRole(roleType);
                newuser.setEmail(email);
                newuser.setUserId(uniqueId);
                loginRepo.save(newuser);
                return jwtService.generateToken(email, "random String", roleType, "None" );
            }
            return jwtService.generateToken(email, "random String", roleType, "None");
        }

        return "notSent";

    }





}
