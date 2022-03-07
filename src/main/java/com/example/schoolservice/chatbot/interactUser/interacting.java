package com.example.schoolservice.chatbot.interactUser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class interacting {
    public String getInformation(String content, String id) {
        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject.get(id).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getUtterance(String content) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            Object userRequest = jsonObject.get("userRequest");
            //userRequest
            obj = jsonParser.parse(userRequest.toString());
            jsonObject = (JSONObject) obj;
            return jsonObject.get("utterance").toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getUserId(String content) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;
            Object userRequest = jsonObject.get("userRequest");
            //userRequest
            obj = jsonParser.parse(userRequest.toString());
            jsonObject = (JSONObject) obj;
            String user = jsonObject.get("user").toString();

            obj = jsonParser.parse(user);
            jsonObject = (JSONObject) obj;
            return jsonObject.get("id").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}