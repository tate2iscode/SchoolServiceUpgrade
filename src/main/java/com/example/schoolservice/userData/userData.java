package com.example.schoolservice.userData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class userData {
    //public static HashMap<String,String> DataSaves;
    public static HashMap<String,String> RequestDatas(String userName) {
        HashMap<String,String> map_from_file = new HashMap<String,String>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("datas/membersCoinDatas/"+userName+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            map_from_file.put(key, properties.get(key).toString());
        }
        return map_from_file;
    }

    public static void SaveDatas(HashMap<String,String> data, String userName) {
        Properties properties = new Properties();
        HashMap<String, String> result = new HashMap<>();
        for(String key : data.keySet()) {
            result.put(key,String.valueOf(data.get(key)));
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        try {
            properties.store(new FileOutputStream("datas/membersCoinDatas/"+userName+".txt"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}