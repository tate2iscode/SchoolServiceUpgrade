package com.example.schoolservice.userData;

import com.example.schoolservice.chatbot.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Map;
import java.util.Objects;

import static com.example.schoolservice.chatbot.interactUser.interacting.getUserId;
import static com.example.schoolservice.chatbot.responseMessage.responsemessage;
import static com.example.schoolservice.userData.userDAO.getConnection;

@Controller
public class userDatasController {

    @RequestMapping("join/{id}")
    @ResponseBody
    public String webjoin(@PathVariable String id) {
        String result = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>InputInformation</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/join/"+id+"\">\n" +
                "        <h3>입력하세요 ㅋ</h3>\n" +
                "        <input type=\"text\" name=\"userSchool\" placeholder=\"학교이름\"><br>\n" +
                "        <input type=\"text\" name=\"userGrade\" placeholder=\"학년\"><br>\n" +
                "        <input type=\"text\" name=\"userClass\" placeholder=\"반\"><br>\n" +
                "        <input type=\"submit\" value=\"제출\">\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";

        String r = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>InputInformation</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"text-align: center;\">\n" +
                "        <form action=\"/joining/"+id+"\">\n" +
                "            <h3>입력하세요 ㅋ</h3>\n" +
                "            <input type=\"text\" name=\"userSchool\" placeholder=\"학교이름\"><br>\n" +
                "            <input type=\"text\" name=\"userGrade\" placeholder=\"학년\"><br>\n" +
                "            <input type=\"text\" name=\"userClass\" placeholder=\"반\"><br>\n" +
                "            <input type=\"submit\" value=\"제출\">\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return r;
    }

    @RequestMapping("/joining/{id}")
    @ResponseBody
    public String webUserDatasInput(@PathVariable String id, userDTO userDTO) {
        userDAO userDAO = com.example.schoolservice.userData.userSchool.userDAO;
        userDAO.setting();

        if(userDAO.confirm(id)) {
            userDTO.setUserId(id);
            userDAO.update(userDTO);
        } else {
            userDTO.setUserId(id);
            userDAO.insert(userDTO);
        }
        return "<div style=\"text-align: center;\">완료</div>";
    }

    @RequestMapping("/setting/url")
    @ResponseBody
    public Response webUserResult(@RequestBody Map<String, Object> content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        jsonString = objectMapper.writeValueAsString(content);
        String userId = getUserId(jsonString);


        return responsemessage("이 url에 들어가서 정보를 입력하세요 \n http://chlgusgh.kro.kr:8080/join/"+userId);
    }

    @RequestMapping("/userid")
    @ResponseBody
    public Response confirmuser(@RequestBody Map<String,Object> content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        jsonString = objectMapper.writeValueAsString(content);
        String userId = getUserId(jsonString);

        return responsemessage(userId);
    }
}
