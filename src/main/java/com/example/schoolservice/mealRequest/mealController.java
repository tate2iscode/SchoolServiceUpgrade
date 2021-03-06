package com.example.schoolservice.mealRequest;

import com.example.schoolservice.chatbot.Response;
import com.example.schoolservice.userData.userDAO;
import com.example.schoolservice.userData.userDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.schoolservice.chatbot.interactUser.interacting.getUserId;
import static com.example.schoolservice.chatbot.responseMessage.responsemessage;
import static com.example.schoolservice.mealRequest.requestMeal.requestmeal;
import static com.example.schoolservice.userData.getFileSchoolInfo.schoolinfo;
import static com.example.schoolservice.userData.userDAO.getConnection;

@Controller
public class mealController {
    //public static userDAO userDAO = null;
    //public userSchool userSchool = new userSchool();
    @RequestMapping("schooltest/{schoolName}")
    @ResponseBody
    public HashMap<String, String> schoolTest(@PathVariable("schoolName") String schoolName){
        return schoolinfo(schoolName);
    }


    @RequestMapping(value = "interact/{time}")
    @ResponseBody
    public Response interactMeal(@PathVariable("time") Integer time, @RequestBody Map<String,Object> content) throws JsonProcessingException {
        if(content == null) {
            return responsemessage("null");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        jsonString = objectMapper.writeValueAsString(content);
        String userId = getUserId(jsonString);

        userDAO userDAO = com.example.schoolservice.userData.userSchool.userDAO;

        userDAO.SqlTest(Objects.requireNonNull(getConnection()), "user");

        userDTO userDTO = userDAO.getInformation(userId);
        String meal = requestmeal(userDTO.getUserSchool(),2);

        return responsemessage(meal);
    }

    @RequestMapping(value = "meal/{time}")
    @ResponseBody
    public Response test(@PathVariable("time") Integer time, @RequestBody Map<String,Object> content) throws JsonProcessingException {
        if(content == null) {
            return responsemessage("null");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        jsonString = objectMapper.writeValueAsString(content);
        String userId = getUserId(jsonString);

        userDAO userDAO = com.example.schoolservice.userData.userSchool.userDAO;
        userDAO.setting();
        //if(userDAO.conn == null) {
        //    userDAO.SqlTest(Objects.requireNonNull(getConnection()), "user");
        //}

        if(userDAO.confirm(userId)) {
            userDTO userDTO = userDAO.getInformation(userId);
            String meal = requestmeal(userDTO.getUserSchool(),time);
            String add = getTime()+" "+userDTO.getUserSchool()+" ";
            return responsemessage(textLineChange(add+meal));
        }else {
            return responsemessage("?????? ????????? ????????????");
        }


    }

    public String textLineChange(String text) {
        String[] line = text.split(" ");
        StringBuilder result = new StringBuilder();
        for(String ap : line) {
            result.append(ap).append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }

    public String getTime() {
        SimpleDateFormat kTime = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        Date date = new Date();
        String time = kTime.format(date);
        return time;
    }
}
