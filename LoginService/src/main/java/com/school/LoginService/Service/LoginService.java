package com.school.LoginService.Service;

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
import org.springframework.beans.factory.annotation.Autowired;
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
    public AdminRepo adminRepo;

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


    public String generateToken(String franchiseId, String emailId, String roleType) {
        return jwtService.generateToken(franchiseId, emailId, roleType);
    }


    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


//    public HashMap<String, String> loginGeneratetoken(LoginModel loginDetails){
//        HashMap<String,String> response =  new HashMap<String,String>();
//        LoginModel loginInfo = loginRepo.findByEmail(loginDetails.getEmail());
//        if (loginInfo != null) {
//            String userPassword = loginInfo.getPassword();
//            if(passwordEncoder.matches(loginDetails.getPassword(), userPassword)) {
//                System.out.println(loginInfo.getServiceId()+" this is franhciseId of user");
//                System.out.println(loginInfo.getRole()+" this is role of user");
//                Admin franchiseAdmin = adminRepo.findByAdminId(loginInfo.getServiceId());
//                if(franchiseAdmin == null){
//                    System.out.println("franchise admin not found");
//                    response.put("status", "failed");
//                    response.put("msg", "user not found");
//                    return response;
//                }
//                String token =  generateToken(loginInfo.getServiceId(), loginInfo.getEmail(), loginInfo.getRole());
//                response.put("token", token);
//                response.put("status","success");
//                response.put("msg", "user logged in");
//                return response;
//            }
//
//            response.put("status","failed");
//            response.put("msg", "Invalid Password");
//            return response;
//        }
//        response.put("status", "failed");
//        response.put("msg", "Invalid EmailId");
//        return  response;
//    }



    public String updatePassword(String email, String password, int otp){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(email);

        Otp verify = otpRepo.getReferenceByemail(email);

        if(verify != null){
            if(verify.getOtp() == otp){

                if(verify.getExpires().isBefore(now)){
                    return "otp expired";
                }else{
                    LoginModel user = loginRepo.findByEmail(email);
                    if(user != null){

                        user.setPassword(passwordEncoder.encode(password));
                        loginRepo.save(user);
                        return "password updated";
                    }else {
                        System.out.println("user is null in login Repo");
                    }
                    return "user not found";
                }
            }
            return "otp invalid";
        }
        return "user not found";
    }




//    public HashMap<String, String> sendOtpAndIssueToken(String email){
//        Random random = new Random();
//        int otp = random.nextInt(1001, 9999);
//        String body = "This is your OTP : "+ otp;
//        String subject = "OTP For Password Reset";
//
//        String token;
//        HashMap<String,String> response = new HashMap<>();
//
//        // Retreiving If User Information Exists
//
//        try{
//            otpRepo.deleteAllByemail(email);
//            System.out.println("old OTP deleted");
//        }catch (Exception e){
//            System.out.println("not found in OTP Table");
//        }
//
//        LoginModel loginUser = loginRepo.findByEmail(email);
//        if(loginUser != null){
//            token = utilitiesService.getOtpSetUser(email, loginUser.getServiceId(), subject, body, otp, loginUser.getRole() );
//            if(!token.equals("notSent")){
//                System.out.println("From login table");
//                response.put("token", token);
//                response.put("status","success");
//                response.put("msg", "OTP Sent to User");
//                return response;
//            }
//        }
//
//
//        StudentModel student = studentServiceFeign.isStudentFound(email);
//        if(student != null){
//            System.out.println("from Login Service, student: "+ student.getEmail());
//            String roleType = staffServiceFeign.getUserRoleType(email);
//            token = utilitiesService.getOtpSetUser(email, student.getFranchiseId(), subject, body, otp, roleType);
//
//            if(!token.equals("notSent")){
//                response.put("token", token);
//                response.put("status", "success");
//                response.put("msg", "OTP Sent to User");
//                return response;
//            }
//
//        }
//
//        TeacherModel teacher = staffServiceFeign.isStaffFound(email);
//        if(teacher != null){
//            System.out.println("From Login Service, teacher:"+teacher.getEmail());
//            String roleType = staffServiceFeign.getUserRoleType(email);
//            token = utilitiesService.getOtpSetUser(email, teacher.getFranchiseId(), subject, body, otp, roleType);
//            if(!token.equals("notSent")){
//                response.put("token", token);
//                response.put("status","success");
//                response.put("msg", "OTP Sent to User");
//                return response;
//            }
//
//        }
//
//        Admin admin = adminRepo.findByAdminEmail(email);
//        if(admin != null){
//            System.out.println("From Login Service, Admin :"+admin.getAdminEmail());
//            String roleType = admin.getAdminRole();
//            token = utilitiesService.getOtpSetUser(email, admin.getAdminId(), subject, body, otp, roleType);
//            if(!token.equals("notSent")){
//                response.put("token", token);
//                response.put("status","success");
//                response.put("msg", "OTP Sent to User");
//                return response;
//            }
//        }
//
//        response.put("token","None");
//        response.put("status","failed");
//        response.put("msg", "User Not Found");
//
//
//        return response;
//    }


}