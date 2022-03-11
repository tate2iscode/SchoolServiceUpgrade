package com.example.schoolservice;

import com.example.schoolservice.chatbot.Response;
import com.example.schoolservice.chatbot.responseMessage;
import com.example.schoolservice.userData.userDAO;
import com.example.schoolservice.userData.userDTO;

import java.sql.*;

import static com.example.schoolservice.chatbot.responseMessage.responsemessage;
import static com.example.schoolservice.mealRequest.requestMeal.*;
import static com.example.schoolservice.userData.getFileSchoolInfo.readCSV;
import static com.example.schoolservice.userData.getFileSchoolInfo.schoolinfo;
import static com.example.schoolservice.userData.userDAO.getConnection;


public class test {
    public static void main(String[] args) {
        userDAO test = new userDAO();
        test.SqlTest(getConnection(),"user");
        System.out.println(test.table);
        test.selectAll();
        System.out.println("---------------------------------------------");
        //System.out.println(test.confirm("test1"));
        userDTO userinfo = new userDTO();
        userinfo.setUserId("856772");
        userinfo.setUserClass("testclasss2344");
        userinfo.setUserSchool("진주동명고등학교");
        userinfo.setUserGrade("testGradenla234f");
        //test.update(userinfo);
        test.insert(userinfo);
        System.out.println(test.getInformation("test12").getUserClass());
        //test.delete("test12");
        //test.selectAll();
    }
}
