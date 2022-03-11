package com.example.schoolservice.userData;

import java.sql.*;
import java.util.Objects;

public class userDAO {
    public Connection conn = null;
    Statement stmt = null;
    public String table;

    public void SqlTest(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        try {
            this.stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insert(userDTO userinfo) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into "+ table + " values('")
                .append(userinfo.getUserId()+"','")
                .append(userinfo.getUserSchool() +"','")
                .append(userinfo.getUserGrade() + "','")
                .append(userinfo.getUserClass() + "');").toString();
        //System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(String userId) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from " + table + " where userId = '")
                .append(userId)
                .append("';")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void update(userDTO userinfo) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update ")
                .append(table)
                .append(" set userSchool='")
                .append(userinfo.getUserSchool())
                .append("' , userGrade = '")
                .append(userinfo.getUserGrade())
                .append("', userClass = '")
                .append(userinfo.getUserClass())
                .append("' where userId = '")
                .append(userinfo.getUserId())
                .append("';").toString();
        //System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean confirm(String userId) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select exists (select * from "+table+" where userId='"+userId+"' limit 1) as success;").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            //System.out.println(rs.getString("success"));
            return Objects.equals(rs.getString("success"), "1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public userDTO getInformation(String userId) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from "+table+" where userId = '"+userId+"';").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            userDTO userDTO = new userDTO();
            userDTO.setUserClass(rs.getString("userClass"));
            userDTO.setUserGrade(rs.getString("userGrade"));
            userDTO.setUserId(rs.getString("userId"));
            userDTO.setUserSchool(rs.getString("userSchool"));
            return userDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void selectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table).append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("userId");
            System.out.print("\t");
            System.out.print("userSchool");
            System.out.print("\t");
            System.out.print("userGrade");
            System.out.print("\t");
            System.out.print("userClass");
            System.out.print("\n");
            System.out.println("────────────────────────");

            while(rs.next()){
                System.out.print(rs.getString("userId"));
                System.out.print("\t");
                System.out.print(rs.getString("userSchool"));
                System.out.print("\t");
                System.out.print(rs.getString("userGrade"));
                System.out.print("\t");
                System.out.print(rs.getString("userClass"));
                System.out.print("\n");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        try {

            System.out.println("[ 접속 완료 ]");
            return DriverManager.getConnection(dbURL, dbId, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("실패1");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("실패2");
            e.printStackTrace();
        }
        return null;
    }
}
