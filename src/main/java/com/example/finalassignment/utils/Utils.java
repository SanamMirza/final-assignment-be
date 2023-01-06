package com.example.finalassignment.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utils {
    public static String getErrorString(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }
}
