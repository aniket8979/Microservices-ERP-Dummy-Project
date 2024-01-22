package com.school.StaffService.Service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDate;


@Component
public class Dry {


    public String generateRecordId(String uniqueId, String type) {
        final String ALPHA_NUMERIC = "abcdefghijkmnoprstuv0123456789";

        // SCR TCH 24 jkd4


        int time = LocalDate.now().getYear() - 2000;
        String recordUser = uniqueId+type+time;

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        String recordUserId;

        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(ALPHA_NUMERIC.length());
            char randomChar = ALPHA_NUMERIC.charAt(randomIndex);
            sb.append(randomChar);
        }
        String randomAlphaNumeric = sb.toString();
        recordUserId = recordUser+randomAlphaNumeric;
        System.out.println("this is recordId new "+recordUserId);

        return recordUserId;
    }

    public String generateRecordId(String uniqueId, String type, int length) {
        final String ALPHA_NUMERIC = "abcdefghijkmnoprstuv0123456789";

        int time = LocalDate.now().getYear() - 2000;
        String recordUser = uniqueId+type+time;

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        String recordUserId;

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHA_NUMERIC.length());
            char randomChar = ALPHA_NUMERIC.charAt(randomIndex);
            sb.append(randomChar);
        }
        String randomAlphaNumeric = sb.toString();
        recordUserId = recordUser+randomAlphaNumeric;
        System.out.println("this is recordId new "+recordUserId);

        return recordUserId;
    }







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
