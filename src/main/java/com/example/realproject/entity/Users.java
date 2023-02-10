package com.example.realproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Users {
    private String uid;
    private String name;
    private String avatar;

    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date brithday;
    private String phone;
    private String type;
    private Integer state;
    private String password;
    private Integer R_id=0;
    private String address;
    private Integer TryCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date LastTime;

    private Integer result;

    private List<permissions> priList ;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTryCount() {
        return TryCount;
    }

    public void setTryCount(Integer tryCount) {
        TryCount = tryCount;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public String getNormalTime(){
        SimpleDateFormat dateFornat = new SimpleDateFormat("E yyyy-MM-dd HH:mm:ss z");
        String date_String = dateFornat.format(LastTime);
        return date_String;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNormalBrithday(){
        SimpleDateFormat dateFornat = new SimpleDateFormat("yyyy-MM-dd");
        String date_String = dateFornat.format(brithday);
        return date_String;
    }
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Integer getR_id() {
        return R_id;
    }

    public void setR_id(Integer r_id) {
        R_id = r_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<permissions> getPriList() {
        return priList;
    }

    public void setPriList(List<permissions> priList) {
        this.priList = priList;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", brithday=" + brithday +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", state=" + state +
                ", password='" + password + '\'' +
                ", R_id=" + R_id +
                ", address='" + address + '\'' +
                ", TryCount=" + TryCount +
                ", LastTime=" + LastTime +
                ", result=" + result +
                ", priList=" + priList +
                '}';
    }
}
