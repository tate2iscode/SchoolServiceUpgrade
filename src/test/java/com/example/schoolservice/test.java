package com.example.schoolservice;

import com.example.schoolservice.chatbot.Response;
import com.example.schoolservice.chatbot.responseMessage;

import static com.example.schoolservice.chatbot.responseMessage.responsemessage;
import static com.example.schoolservice.mealRequest.requestMeal.*;
import static com.example.schoolservice.userData.getFileSchoolInfo.readCSV;
import static com.example.schoolservice.userData.getFileSchoolInfo.schoolinfo;


public class test {
    public static void main(String[] args) {
        //System.out.println(responsemessage("hi"));
        //System.out.println(dayTime());
        //System.out.println(readCSV("data/schoolinfo/학교기본정보.csv"));
        //System.out.println(schoolinfo("진주명고등학교"));
        System.out.println(requestmeal("진주제일여자고학교",2));
    }
}
