package com.school.StaffService.Service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@Component
public class Dry {



    public void fileSave(MultipartFile myfile, String path){
        MultipartFile file = myfile;
        String fpath = path;

        if (file != null && !file.isEmpty()){

            String filename;
            filename = file.getOriginalFilename();

            fpath = fpath+"/"+filename;
            Path filepath = Paths.get(fpath);
            byte[] bytes;
            try {
                bytes = file.getBytes();
                Files.write(filepath, bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void fileSave(MultipartFile myfile, String path, String filename){
        MultipartFile file = myfile;
        String fpath = path+"/"+filename;
        if (file != null && !file.isEmpty()){
            Path filepath = Paths.get(fpath);
            byte[] bytes;
            try {
                bytes = file.getBytes();
                Files.write(filepath, bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }



    public String profilePicPath = "/Users/Aniket/Scriza/Projects/Project/school/files/dp";

    public String docFilepath = "/Users/Aniket/Scriza/Projects/Project/school/files/";





}
