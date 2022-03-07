package com.example.schoolservice.chatbot;

import static com.example.schoolservice.mealRequest.requestMeal.requestmeal;

public class responseMessage {
    public static Response responsemessage(String text) {
        Message message = new Message();
        //DongmyungMealRequest meal = new DongmyungMealRequest();
        message.setText(text);

        SimpleText simpleText = new SimpleText();
        simpleText.setSimpleText(message);

        OutPuts outPuts = new OutPuts();
        outPuts.setOutputs(new SimpleText[] {simpleText});

        Response response = new Response();
        response.setVersion("2.0");
        response.setTemplate(outPuts);

        return response;
    }
}
