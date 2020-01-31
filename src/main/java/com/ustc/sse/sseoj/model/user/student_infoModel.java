package com.ustc.sse.sseoj.model.user;

public class student_infoModel {
    private String no;

    private Short sage;

    private String ssex;

    private String sgrade;

    private String sphoneNumber;

    private String semail;

    private String sdept;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Short getSage() {
        return sage;
    }

    public void setSage(Short sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex == null ? null : ssex.trim();
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade == null ? null : sgrade.trim();
    }

    public String getSphoneNumber() {
        return sphoneNumber;
    }

    public void setSphoneNumber(String sphoneNumber) {
        this.sphoneNumber = sphoneNumber == null ? null : sphoneNumber.trim();
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail == null ? null : semail.trim();
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept == null ? null : sdept.trim();
    }
}