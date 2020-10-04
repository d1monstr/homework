package ru.appline.frameworks.rencredit.utils;

public class NumbersUtil {

    public static String cleanNumber(String numberString){
        String number = "";
        String numbers = "0987654321.,";
        for (int i = 0; i < numberString.length(); ++i) {
            if (numbers.contains("" + numberString.charAt(i))) {
                number += numberString.charAt(i);
            }
        }
        return number;
    }

}
