package com.ustc.sse.sseoj.model.user.uniteModel;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/21  22:47
 */
public class student_uniteModel {
    private String no;

    private String password;

    private String name;

    private String role;

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
        this.no = no;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        this.ssex = ssex;
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade;
    }

    public String getSphoneNumber() {
        return sphoneNumber;
    }

    public void setSphoneNumber(String sphoneNumber) {
        this.sphoneNumber = sphoneNumber;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    @Override
    public String toString() {
        return "student_uniteModel{" +
                "no='" + no + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", sage=" + sage +
                ", ssex='" + ssex + '\'' +
                ", sgrade='" + sgrade + '\'' +
                ", sphoneNumber='" + sphoneNumber + '\'' +
                ", semail='" + semail + '\'' +
                ", sdept='" + sdept + '\'' +
                '}';
    }
}
