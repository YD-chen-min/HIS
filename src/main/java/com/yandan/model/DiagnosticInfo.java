package com.yandan.model;


//诊断信息
public class DiagnosticInfo {
    private String id;//诊断id
    private String registrationId;//挂号单号
    private String selfReported;//主诉
    private String accidentDate;//发病日期
    private String treatmentDate;//就诊日期
    private String conclusion;//诊断结论
    private String remark;//医嘱

    public DiagnosticInfo() {
    }

    public DiagnosticInfo(String id, String registrationId, String selfReported, String accidentDate, String treatmentDate, String conclusion, String remark) {
        this.id = id;
        this.registrationId = registrationId;
        this.selfReported = selfReported;
        this.accidentDate = accidentDate;
        this.treatmentDate = treatmentDate;
        this.conclusion = conclusion;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getSelfReported() {
        return selfReported;
    }

    public void setSelfReported(String selfReported) {
        this.selfReported = selfReported;
    }

    public String getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(String treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
