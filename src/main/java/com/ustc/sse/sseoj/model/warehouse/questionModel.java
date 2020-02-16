package com.ustc.sse.sseoj.model.warehouse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/2/4 14:52
 */
@ToString
public class questionModel {
    private String questionid;

    private String questiontype;

    @Getter
    @Setter
    private int questionNumber;

    private String title;

    private String description;

    private String pictureUrl;

    private String inputExplain;

    private String outputExplain;

    private String annotation;

    private Integer timelimit;

    private Integer memorylimit;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype == null ? null : questiontype.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getInputExplain() {
        return inputExplain;
    }

    public void setInputExplain(String inputExplain) {
        this.inputExplain = inputExplain == null ? null : inputExplain.trim();
    }

    public String getOutputExplain() {
        return outputExplain;
    }

    public void setOutputExplain(String outputExplain) {
        this.outputExplain = outputExplain == null ? null : outputExplain.trim();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation == null ? null : annotation.trim();
    }

    public Integer getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Integer timelimit) {
        this.timelimit = timelimit;
    }

    public Integer getMemorylimit() {
        return memorylimit;
    }

    public void setMemorylimit(Integer memorylimit) {
        this.memorylimit = memorylimit;
    }

}