package com.bumday.dormitory.LiskVO;

import android.graphics.drawable.Drawable;

public class ListCOM {
    private String Title;
    private String context;
    private String time;

    public String getTitle(){
        return Title;
    }

    public void setTitle(String title){
        Title = title;
    }

    public String getContext(){
        return context;
    }

    public void setContext(String context){
        this.context = context;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }
}
