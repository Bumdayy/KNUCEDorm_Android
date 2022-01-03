package com.bumday.dormitory.DataBase;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

public class Posts implements Parcelable {
    private static final String TAG ="Posts";
    private int num;
    private String title;
    private String post;
    private String name;
    private String time;
    private String id;

    public Posts() {

    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {this.name = name; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTime(String time) { this.time = time;}

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public String getTime() {
        return time;
    }

    public int getNum() {
        return num;
    }

    protected Posts(Parcel parcel) {
        num=parcel.readInt();
        id=parcel.readString();
        name = parcel.readString();
        title = parcel.readString();
        post = parcel.readString();
        time = parcel.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.num);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.post);
        dest.writeString(this.time);
    }
}
