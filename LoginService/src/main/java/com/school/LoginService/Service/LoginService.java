package com.school.LoginService.Service;

import com.school.LoginService.Exception.BadRequestException;
import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.FeignService.StaffServiceFeign;
import com.school.LoginService.FeignService.StudentServiceFeign;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Model.Otp;
import com.school.LoginService.Model.Admin;
import com.school.LoginService.Repo.LoginRepo;
import com.school.LoginService.Repo.OtpRepo;
import com.school.LoginService.Repo.AdminRepo;
import com.school.LoginService.Transient.StudentModel;
import com.school.LoginService.Transient.TeacherModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;



@Service
public class LoginService {

    @Autowired
    public LoginRepo loginRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public AdminRepo superAdminRepo;

    @Autowired
    public StaffServiceFeign staffServiceFeign;

    @Autowired
    public UtilitiesService utilitiesService;

    @Autowired
    public StudentServiceFeign studentServiceFeign;

    @Autowired
    public OtpRepo otpRepo;


    public String SavePassword(LoginModel userdetails){
        userdetails.setPassword(passwordEncoder.encode(userdetails.getPassword()));
        loginRepo.save(userdetails);
        return "Password Changed Successfully";
    }


    public String generateToken(String franchiseId, String emailId, String roleType, String uniqueId) {
        return jwtService.generateToken(franchiseId, emailId, roleType, uniqueId);
    }


    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


    public HashMap<String, String> loginGeneratetoken(LoginModel loginDetails){
        HashMap<String,String> response =  new HashMap<>();
        LoginModel loginInfo = loginRepo.getReferenceByemail(loginDetails.getEmail());
        if (loginInfo != null) {
            String userPassword = loginInfo.getPassword();
            if(passwordEncoder.matches(loginDetails.getPassword(), userPassword)) {
                System.out.println(loginInfo.getSchoolId()+" this is schoolId of user");
                System.out.println(loginInfo.getRole()+" this is role of user");
                String token =  generateToken(loginInfo.getSchoolId(), loginInfo.getEmail(), loginInfo.getRole(), loginInfo.getUserId());
                response.put("token", token);
                response.put("status","success");
                response.put("msg", "user logged in");
                return response;
            }

            response.put("status","failed");
            response.put("msg", "Invalid Password");
            return response;
        }
        response.put("status", "failed");
        response.put("msg", "Invalid EmailId");
        return  response;
    }


    /* This method was created to handle logic related to password
    * it is commented because it is no longer required in present scenario
    * but is kept for future reference
    */

//    public String updatePassword(String email, String password, int otp){
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(email);
//
//        Otp verify = otpRepo.getReferenceByemail(email);
//
//        if(verify != null){
//            if(verify.getOtp() == otp){
//
//                if(verify.getExpires().isBefore(now)){
//                    return "otp expired";
//                }else{
//                    LoginModel user = loginRepo.getReferenceByemail(email);
//                    if(user != null){
//
//                        user.setPassword(passwordEncoder.encode(password));
//                        loginRepo.save(user);
//                        return "password updated";
//                    }else {
//                        System.out.println("user is null in login Repo");
//                    }
//                    return "user not found";
//                }
//            }
//            return "otp invalid";
//        }
//        return "user not found";
//    }




    public boolean updatePassword(String password, String email){
        LocalDateTime now = LocalDateTime.now();
        LoginModel user = loginRepo.getReferenceByemail(email);
        if(user != null){
            user.setPassword(passwordEncoder.encode(password));
            loginRepo.save(user);
            return true;
        }else {
            throw new BadRequestException("User not found");
        }
    }




    public HashMap<String, String> sendOtpAndIssueToken(String email){
        Random random = new Random();
        int otp = random.nextInt(1001, 9999);
        String body = "This is your OTP : "+ otp;
        String subject = "OTP For Password Reset";

        String token;
        HashMap<String,String> response = new HashMap<>();

        // Retreiving If User Information Exists
        try{
            otpRepo.deleteAllByemail(email);
            System.out.println("old OTP deleted");
        }catch (Exception e){
            System.out.println("not found in OTP Table");
        }

        LoginModel loginUser = loginRepo.getReferenceByemail(email);
        if(loginUser != null){
            token = utilitiesService.getOtpSetUser(email, loginUser.getSchoolId(), subject, body, otp, loginUser.getRole(), loginUser.getUserId());
            if(!token.equals("notSent")){
                System.out.println("From login table");
                response.put("token", token);
                response.put("status","success");
                response.put("msg", "OTP Sent to User");
                return response;
            }
        }


        StudentModel student = studentServiceFeign.isStudentFound(email);
        if(student != null){
            System.out.println("from Login Service, student: "+ student.getEmail());
            String roleType = staffServiceFeign.getUserRoleType(email);
            token = utilitiesService.getOtpSetUser(email, student.getSchoolId(), subject, body, otp, roleType, student.getUserId());

            if(!token.equals("notSent")){
                response.put("token", token);
                response.put("status", "success");
                response.put("msg", "OTP Sent to User");
                return response;
            }

        }

        TeacherModel teacher = staffServiceFeign.isStaffFound(email);
        if(teacher != null){
            System.out.println("From Login Service, teacher:"+teacher.getEmail());
            String roleType = staffServiceFeign.getUserRoleType(email);
            token = utilitiesService.getOtpSetUser(email, teacher.getSchoolId(), subject, body, otp, roleType, teacher.getUserId());
            if(!token.equals("notSent")){
                response.put("token", token);
                response.put("status","success");
                response.put("msg", "OTP Sent to User");
                return response;
            }

        }

        Admin admin = superAdminRepo.getReferenceByAdminEmail(email);
        if(admin != null){
            System.out.println("From Login Service, Admin :"+admin.getAdminEmail());
            String roleType = admin.getAdminRole();
            String adminId = String.valueOf(admin.getId());
            System.out.println("this is adminId "+ adminId);
            token = utilitiesService.getOtpSetUser(email, admin.getSchoolId(), subject, body, otp, roleType, adminId);
            if(!token.equals("notSent")){
                response.put("token", token);
                response.put("status","success");
                response.put("msg", "OTP Sent to User");
                return response;
            }
        }

        response.put("token","None");
        response.put("status","failed");
        response.put("msg", "User Not Found");

        return response;
    }



    public boolean verifyotp(String email, int otp) {
        System.out.println("this is email"+email);
        Otp curOtp = otpRepo.findByEmail(email);
        System.out.println("this is OTP "+otp+ " "+curOtp.getOtp());
        return curOtp.getOtp()==otp;
    }




    @Transactional
    public ResponseEntity<?> generatePassword(String email, int otp_mobile) {

        boolean verified = verifyotp(email, otp_mobile);
        if(verified){
            String password = utilitiesService.generateString(10);
            LoginModel userLogin = loginRepo.findByemail(email);
            userLogin.setPassword(passwordEncoder.encode(password));
            loginRepo.save(userLogin);
            boolean mailSent = utilitiesService.sendEmail(email,
                    "new password",
                    "This is your newly generated password, you can change it from inside the setting penal of applicaltion : "+ password);
            if(mailSent) {
                return ResponseClass.responseSuccess("password sent to email");
            }
            return ResponseClass.responseFailure("unable to generate passowrd");
        }
        return ResponseClass.responseFailure("invalid otp");
    }


    public ResponseEntity<?> changeMobilePassword(
            String email,
            String schoolId,
            String userId,
            String roleType,
            String password)
    {
        LoginModel userLoginInfo = loginRepo.findByemail(email);
        if(userLoginInfo.getSchoolId().equals(schoolId)){
            userLoginInfo.setPassword(passwordEncoder.encode(password));
            loginRepo.save(userLoginInfo);
            return ResponseClass.responseSuccess("password changed");
        }
        return ResponseClass.responseFailure("failure");
    }
}