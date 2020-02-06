package com.ustc.sse.sseoj.model.teacher;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
public class teacher_homeworkModelKey {
    private String tno;

    private String homeworkid;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(String homeworkid) {
        this.homeworkid = homeworkid == null ? null : homeworkid.trim();
    }
}