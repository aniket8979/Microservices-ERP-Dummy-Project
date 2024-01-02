package com.school.LoginService.Service;

import com.school.LoginService.FeignService.StaffServiceFeign;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Model.Otp;
import com.school.LoginService.Repo.LoginRepo;
import com.school.LoginService.Repo.OtpRepo;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


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
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public String getOtpSetUser(String email, String franchiseId , String subject, String body, int otp, String roleType){

        Otp otpSent = new Otp();

        try{
            Otp old = otpRepo.getReferenceByemail(email);
            otpRepo.delete(old);
            System.out.println("old OTP deleted");
        }catch (Exception e){
            System.out.println("not found in OTP Table");
        }

        LocalDateTime issued = LocalDateTime.now();
        LocalDateTime expiry = issued.plusSeconds(600);

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
                newuser.setFranchiseId(franchiseId);
                newuser.setRole(roleType);
                newuser.setEmail(email);
                loginRepo.save(newuser);
                return jwtService.generateToken(email, "random String", roleType, "None" );
            }
            return jwtService.generateToken(email, "random String", roleType, "None");
        }

        return "notSent";

    }





}
