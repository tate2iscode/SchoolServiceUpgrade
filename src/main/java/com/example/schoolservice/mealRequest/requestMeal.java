package com.example.schoolservice.mealRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.schoolservice.userData.getFileSchoolInfo.schoolinfo;

public class requestMeal {
    public static String requestmeal(String schoolName, int time) {
        HashMap<String,String> school = schoolinfo(schoolName);
        if(Objects.equals(school.get("exist"), "0")) {
            return "학교_설정을_다시해주세요";
        }

        String url = String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?ATPT_OFCDC_SC_CODE=%s&SD_SCHUL_CODE=%s&MLSV_YMD=%s&Type=json&MMEAL_SC_CODE=%d",school.get("atpt"),school.get("sd"),dayTime(),time); // 2는 중식 3은 석식
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = doc.text();
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray code = (JSONArray) Objects.requireNonNull(jsonObject).get("mealServiceDietInfo");
        if(code == null) {
            return "급식없음";
        }
        String mealService = code.get(1).toString();

        parser = new JSONParser();
        try {
            obj = parser.parse(mealService);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        code = (JSONArray) jsonObject.get("row");
        String row = code.get(0).toString();

        parser = new JSONParser();
        try {
            obj = parser.parse(row);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
        return jsonObject.get("DDISH_NM").toString();
    }

    public static String dayTime() {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        //한국기준 날짜
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return todaySdf.format(date);
    }
}
