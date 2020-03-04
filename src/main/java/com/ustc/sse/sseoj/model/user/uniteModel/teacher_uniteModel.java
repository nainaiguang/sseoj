package com.ustc.sse.sseoj.model.user.uniteModel;

/**
 * @Description TODO
 * @Author 沙政鑫
 * @Data 2020/2/24  11:47
 */
public class teacher_uniteModel {
    private String no;
    private String password;
    private String name;
    private String role;
    private Short tage;
    private String tsex;
    private String tphoneNumber;
    private String temail;
    private String tdept;

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
        this.tsex = tsex;
    }

    public String getTphoneNumber() {
        return tphoneNumber;
    }

    public void setTphoneNumber(String tphoneNumber) {
        this.tphoneNumber = tphoneNumber;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept;
    }

    @Override
    public String toString() {
        return "teacher_uniteModel{" +
                "no='" + no + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", tage=" + tage +
                ", tsex='" + tsex + '\'' +
                ", tphoneNumber='" + tphoneNumber + '\'' +
                ", temail='" + temail + '\'' +
                ", tdept='" + tdept + '\'' +
                '}';
    }
}
