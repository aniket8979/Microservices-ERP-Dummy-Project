package com.school.StudentService.Service;

import java.security.SecureRandom;
import java.time.LocalDate;

public class UtilitiesServices {

    public static String generateRecordId(String uniqueId, String type) {
        final String ALPHA_NUMERIC = "abcdefghijkmnoprstuv0123456789";

        int time = LocalDate.now().getYear() - 2000;
        String year = String.valueOf(time-2000);
        String recordUser = uniqueId+type+year;

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

        return recordUserId;
    }







}

