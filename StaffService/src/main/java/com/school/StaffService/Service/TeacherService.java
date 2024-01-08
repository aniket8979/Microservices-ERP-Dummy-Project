package com.school.StaffService.Service;


import com.school.StaffService.Model.Document;
import com.school.StaffService.Model.Roles;
import com.school.StaffService.Model.TeacherModel;
import com.school.StaffService.Repo.DocRepo;
import com.school.StaffService.Repo.RolesRepo;
import com.school.StaffService.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    public TeacherRepo teacherRepo;

    @Autowired
    public DocRepo docRepo;

    @Autowired
    public RolesRepo roleRepo;

    @Autowired
    public Dry handleFile;



    public TeacherModel createNewTeacher(TeacherModel teacher){
        return teacherRepo.save(teacher);
    }



    public TeacherModel findOneByUserId(String userid){
        return teacherRepo.findByuserId(userid);
    }




    public Roles saveRole(Roles userrole, String franchiseId){
        userrole.setFranchiseId(franchiseId);
        return roleRepo.save(userrole);
    }



     public boolean saveDocRegister(List<Document> userdoc, Map<String, MultipartFile> files, String userId, String franchiseId) {
         int itr = 0;
         if(!files.isEmpty()) {
             for(int i = 0; i < userdoc.size(); i++) {

                 Document doc = userdoc.get(itr);
                 String fname = userId + itr;
                 String fpath = handleFile.docFilepath + fname;
                 MultipartFile file = files.get(doc.getDocType());

                 if(file != null && !file.isEmpty()) {
                     Path path = Paths.get(fpath);
                     byte[] bytes;
                     try {
                         bytes = file.getBytes();
                         Files.write(path, bytes);
                         itr = itr + 1;
                     } catch (IOException e) {
                         throw new RuntimeException("unable to save files");
                     }
                     try {
                         doc.setDocPath(fpath);
                         doc.setDocUserId(userId);
                         doc.setFranchiseId(franchiseId);
                         docRepo.save(doc);
                         return true;

                     } catch (Exception e) {
                         System.out.println("Some Error With DocUser: " + e);
                     }
                 }else{
                     return false;
                 }
             }
             return true;
         }
         return false;
     }




}
