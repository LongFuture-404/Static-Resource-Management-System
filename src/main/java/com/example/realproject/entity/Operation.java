package com.example.realproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Operation {

    private String ip;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date LastTime;
    private String phone;
    private String operation;

    private String bool=null;

    public String getBool() {
        return bool;
    }

    public void setBool(String bool) {
        this.bool = bool;
    }

    public Operation(String ip, Date lastTime, String phone, String operation) {
        this.ip = ip;
        this.LastTime = lastTime;
        this.phone = phone;
        this.operation = operation;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "ip='" + ip + '\'' +
                ", LastTime=" + LastTime +
                ", phone='" + phone + '\'' +
                ", operation='" + operation + '\'' +
                ", bool=" + bool +
                '}';
    }
}
