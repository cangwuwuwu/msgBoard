package com.cangwu.domain;

/**
 * @Author: Cangwu
 * @Date: 2020/4/8 13:18
 */
public class Message<T> {

    private Integer id;
    private Integer status;
    private String mname;
    private String mmsg;
    private String mtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMmsg() {
        return mmsg;
    }

    public void setMmsg(String mmsg) {
        this.mmsg = mmsg;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", status=" + status +
                ", mname='" + mname + '\'' +
                ", mmsg='" + mmsg + '\'' +
                ", mtime='" + mtime + '\'' +
                '}';
    }
}
