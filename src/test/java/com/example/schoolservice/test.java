package com.example.schoolservice;

import com.example.schoolservice.userData.timeDTO;
import com.example.schoolservice.userData.userDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class test {
    public static void main(String[] args) {
        userDAO userDAO = new userDAO();
        userDAO.setTime();
        timeDTO timeDTO = userDAO.setTime();
        System.out.println(timeDTO.getHour());
        
    }
}
