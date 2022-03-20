package com.example.schoolservice.subjectRequest;

import java.util.HashMap;
import java.util.Objects;

import static com.example.schoolservice.userData.getFileSchoolInfo.schoolinfo;

public class requestSubject {
    public static String requestsubject(String schoolName) {
        HashMap<String,String> school = schoolinfo(schoolName);
        if(Objects.equals(school.get("exist"),"0")) {
            return "학교_설정을_다시해주세요";
        }

        String url = null;
        return "";
    }
}
