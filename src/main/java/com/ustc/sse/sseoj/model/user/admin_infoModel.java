package com.ustc.sse.sseoj.model.user;

public class admin_infoModel {
    private String no;

    private String aage;

    private String asex;

    private String aphoneNumber;

    private String aemail;

    private String adept;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getAage() {
        return aage;
    }

    public void setAage(String aage) {
        this.aage = aage == null ? null : aage.trim();
    }

    public String getAsex() {
        return asex;
    }

    public void setAsex(String asex) {
        this.asex = asex == null ? null : asex.trim();
    }

    public String getAphoneNumber() {
        return aphoneNumber;
    }

    public void setAphoneNumber(String aphoneNumber) {
        this.aphoneNumber = aphoneNumber == null ? null : aphoneNumber.trim();
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail == null ? null : aemail.trim();
    }

    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {
        this.adept = adept == null ? null : adept.trim();
    }
}