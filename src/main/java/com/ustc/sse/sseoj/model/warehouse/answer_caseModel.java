package com.ustc.sse.sseoj.model.warehouse;

public class answer_caseModel {
    private String answercaseid;

    private String caseInput;

    private String caseOutput;

    public String getAnswercaseid() {
        return answercaseid;
    }

    public void setAnswercaseid(String answercaseid) {
        this.answercaseid = answercaseid == null ? null : answercaseid.trim();
    }

    public String getCaseInput() {
        return caseInput;
    }

    public void setCaseInput(String caseInput) {
        this.caseInput = caseInput == null ? null : caseInput.trim();
    }

    public String getCaseOutput() {
        return caseOutput;
    }

    public void setCaseOutput(String caseOutput) {
        this.caseOutput = caseOutput == null ? null : caseOutput.trim();
    }
}