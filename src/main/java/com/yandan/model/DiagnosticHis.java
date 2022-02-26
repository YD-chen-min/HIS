package com.yandan.model;

import java.util.Date;
//诊断记录
public class DiagnosticHis {
    private String id;//诊断id
    private String patientId;//病人身份证
    private String selfReported;//主诉
    private String accidentDate;//发病日期
    private String treatmentDate;//就诊日期
    private String conclusion;//诊断结论
    private String remark;//医嘱
    private String doctorId;//医生身份证

    public DiagnosticHis() {
    }

    public DiagnosticHis(String id, String patientId, String selfReported, String accidentDate, String treatmentDate, String conclusion, String remark, String doctorId) {
        this.id = id;
        this.patientId = patientId;
        this.selfReported = selfReported;
        this.accidentDate = accidentDate;
        this.treatmentDate = treatmentDate;
        this.conclusion = conclusion;
        this.remark = remark;
        this.doctorId = doctorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
