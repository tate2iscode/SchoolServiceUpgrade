package com.example.schoolservice.mealRequest;

import com.example.schoolservice.chatbot.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.schoolservice.chatbot.interactUser.interacting.getUserId;
import static com.example.schoolservice.chatbot.responseMessage.responsemessage;
import static com.example.schoolservice.mealRequest.requestMeal.requestmeal;

@Controller
public class mealController {
    @RequestMapping(value = "meal/{time}")
    @ResponseBody
    public Response meal(@PathVariable("time") Integer time) {
        return responsemessage(requestmeal("진주동명고등학교",time));
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
        return responsemessage(userId);
    }
}
