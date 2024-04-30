package com.example.project.Untilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomValidate {
    public static boolean validateText(String text) {
        if (text.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateNumber(String number, String fieldName) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
