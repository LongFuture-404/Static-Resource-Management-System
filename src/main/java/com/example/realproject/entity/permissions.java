package com.example.realproject.entity;

import java.util.List;

public class permissions {

    private Integer p_id ;
    private String p_name;
    private String p_url ;
    private Integer p_sort ;
    private Boolean p_able;
    private Integer p_pid;
    private List<permissions> priList;

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_url() {
        return p_url;
    }

    public void setP_url(String p_url) {
        this.p_url = p_url;
    }

    public Integer getP_sort() {
        return p_sort;
    }

    public void setP_sort(Integer p_sort) {
        this.p_sort = p_sort;
    }

    public Boolean getP_able() {
        return p_able;
    }

    public void setP_able(Boolean p_able) {
        this.p_able = p_able;
    }

    public Integer getP_pid() {
        return p_pid;
    }

    public void setP_pid(Integer p_pid) {
        this.p_pid = p_pid;
    }

    public List<permissions> getPriList() {
        return priList;
    }

    public void setPriList(List<permissions> priList) {
        this.priList = priList;
    }

    @Override
    public String toString() {
        return "permissions{" +
                "p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_url='" + p_url + '\'' +
                ", p_sort=" + p_sort +
                ", p_able=" + p_able +
                ", p_pid=" + p_pid +
                ", priList=" + priList +
                '}';
    }
}
