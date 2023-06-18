package com.example.demotesting.validator;

import org.apache.commons.lang3.StringUtils;

public class EmployeeValidator {
    public static boolean validate(String firsName, String lastName){
        return StringUtils.isAlpha(firsName) && StringUtils.isAlpha(lastName);
    }

}
