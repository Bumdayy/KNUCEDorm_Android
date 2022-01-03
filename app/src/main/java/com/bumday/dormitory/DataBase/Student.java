package com.bumday.dormitory.DataBase;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable
{
    String id;
    String name;
    String pw;
    String address;
    String major;
    String phone;
    String dom_kind;
    String room;
    public Student(){}
    public Student(Parcel parcel) {
        id = parcel.readString();
        name = parcel.readString();
        pw = parcel.readString();
        address = parcel.readString();
        major = parcel.readString();
        phone = parcel.readString();
        dom_kind = parcel.readString();
        room = parcel.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public void setId(String id)
    {
        this.id=id;
    }
    public void setPw(String pw)
    {
       this.pw=pw;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public void setMajor(String major)
    {
        this.major=major;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }
    public void setDom(String dom_kind)
    {
        this.dom_kind=dom_kind;
    }
    public void setRoom(String room)
    {
        this.room=room;
    }


    public String getId()
    {
        return id;
    }
    public String getPw()
    {
        return pw;
    }
    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public String getMajor()
    {
        return major;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getDom()
    {
        return dom_kind;
    }
    public String getRoom()
    {
        return room;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pw);
        dest.writeString(this.address);
        dest.writeString(this.major);
        dest.writeString(this.phone);
        dest.writeString(this.dom_kind);
        dest.writeString(this.room);
    }
}
