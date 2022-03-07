package com.example.schoolservice.userData;

import java.io.*;
import java.util.*;

public class getFileSchoolInfo {
    //이 아래 함수에서 파일을 읽은것을 학교이름만으로 시도교육청 코드와 표준학교코드를 얻어온다.
    public static HashMap<String,String> schoolinfo(String schoolName) {
        HashMap<String,String> result = new HashMap<>();
        //시도교육청코드 0 표준학교코드 2 학교명 3
        result.put("exist","0");
        for(List<String> info : readCSV("data/schoolinfo/학교기본정보.csv")) {
            if(info.size() == 1) {
                return result;
            }
            //System.out.println(info.size());
            if(Objects.equals(info.get(3), schoolName)) {
                result.put("atpt",info.get(0));
                result.put("sd",info.get(2));
                result.put("exist","1");
                return result;
            }
        }
        return result;
    }

    //나이스에서 가져온 파일을 읽는다.
    public static List<List<String>> readCSV(String path) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}
