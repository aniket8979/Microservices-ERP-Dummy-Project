package com.main.config;

import java.security.SecureRandom;
import java.time.LocalDate;

public class UtilitiesServices {

    public static String generateRecordId(String uniqueId, String type, int size) {
        final String ALPHA_NUMERIC = "abcdefghijkmnoprstuv0123456789";

        int time = LocalDate.now().getYear() - 2000;
        String recordUser = uniqueId + type + time;
        //            SCZ00 +st+24+ iwyc == SCZ00ST24IWYC

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        String recordUserId;

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(ALPHA_NUMERIC.length());
            char randomChar = ALPHA_NUMERIC.charAt(randomIndex);
            sb.append(randomChar);
        }
        String randomAlphaNumeric = sb.toString();
        recordUserId = recordUser + randomAlphaNumeric;
        System.out.println("this is recordId new " + recordUserId);

        return recordUserId;
    }
}
