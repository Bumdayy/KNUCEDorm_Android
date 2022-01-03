package com.bumday.dormitory.database;

import com.bumday.dormitory.Admin_1_stuinfomng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Student {

    Admin_1_stuinfomng item;

    private String id;
    private String name;
    private String pwd;
    private String address;
    private String major;
    private String telnum;
    private String dorm;
    private String roomnum;

    public String getid() {return id;}
    public String getname() {return name;}
    public String getpwd(){return pwd;}
    public String getaddress() {return address;}
    public String getmajor() {return major;}
    public String gettelnum() {return telnum;}
    public String getdorm() {return dorm;}
    public String getroomnum() {return roomnum;}

    public void setid(String id) {this.id = id;}
    public void setname(String name) {this.name = name;}
    public void setpwd(String pwd) {this.pwd = pwd;}
    public void setaddress(String address) {this.address = address;}
    public void setmajor(String major) {this.major = major;}
    public void settelnum(String telnum) {this.telnum = telnum;}
    public void setdorm(String dorm) {this.dorm = dorm;}
    public void setroomnum(String roomnum) {this.roomnum = roomnum;}

//    private void jsonParsing(String json)
//    {
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//
//            JSONArray stuArray = jsonObject.getJSONArray("rows");
//
//            for(int i = 0; i <stuArray.length(); i++)
//            {
//                JSONObject stuObject = stuArray.getJSONObject(i);
//
//                Student stuinfo = new Student();
//
//                stuinfo.setid(stuObject.getString("id"));
//                stuinfo.setname(stuObject.getString("name"));
//                stuinfo.setpwd(stuObject.getString("pwd"));
//                stuinfo.setaddress(stuObject.getString("address"));
//                stuinfo.setmajor(stuObject.getString("major"));
//                stuinfo.settelnum(stuObject.getString("telnum"));
//                stuinfo.setdorm(stuObject.getString("dorm"));
//                stuinfo.setroomnum(stuObject.getString("roomnum"));
//
////              items.add(stuinfo);
//            }
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//    }

    // JSON 파싱해서 Admin_1_stuinfo에 List나열
//    public void jsonParsing(String json)
//    {
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//
//            JSONArray stuArray = jsonObject.getJSONArray("rows");
//
//            for(int i = 0; i< stuArray.length(); i++)
//            {
//                JSONObject movieObject = stuArray.getJSONObject(i);
//
//                Student student = new Student();
//
//                student.setid(movieObject.getString("id"));
//                student.setname(movieObject.getString("name"));
//                student.setpwd(movieObject.getString("pwd"));
//                student.setaddress(movieObject.getString("address"));
//                student.setmajor(movieObject.getString("major"));
//                student.settelnum(movieObject.getString("telnum"));
//                student.setdorm(movieObject.getString("dorm"));
//                student.setroomnum(movieObject.getString("roomnum"));
//
//                item.items.add("id :" + student.id );
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
