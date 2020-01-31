package com.ustc.sse.sseoj.model.user;

public class teacher_infoModel {
    private String no;

    private Short tage;

    private String tsex;

    private String tphoneNumber;

    private String temail;

    private String tdept;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Short getTage() {
        return tage;
    }

    public void setTage(Short tage) {
        this.tage = tage;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex == null ? null : tsex.trim();
    }

    public String getTphoneNumber() {
        return tphoneNumber;
    }

    public void setTphoneNumber(String tphoneNumber) {
        this.tphoneNumber = tphoneNumber == null ? null : tphoneNumber.trim();
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail == null ? null : temail.trim();
    }

    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept == null ? null : tdept.trim();
    }
}