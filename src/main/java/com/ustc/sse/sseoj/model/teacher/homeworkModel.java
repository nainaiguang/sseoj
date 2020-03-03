package com.ustc.sse.sseoj.model.teacher;

import com.ustc.sse.sseoj.model.functionClass.pageLimit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
@ToString
public class homeworkModel {

    private String homeworkid;

    private String name;

    private String describes;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date begintime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    @Getter
    private String createtimeStr;
    @Getter
    private String begintimeStr;
    @Getter
    private String endtimeStr;

    public String getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(String homeworkid) {
        this.homeworkid = homeworkid == null ? null : homeworkid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createtimeStr=formatter.format(createtime);
    }

    public Date getBegintime() {
        return begintime;

    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.begintimeStr=formatter.format(begintime);
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.endtimeStr=formatter.format(endtime);
    }
}