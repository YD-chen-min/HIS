package com.yandan.model;


//项目检查记录
public class ProjectHis {
    private String id;//项目检查id
    private String projectId;//项目id
    private float initialCost;//初始金额
    private float finalCost;//最终金额
    private String patientId;//病人身份证
    private String doctorId;//医生身份证
    private float roundingAmount;//舍入金额
    private int status;//状态 -1已取消 0已完成 1未缴费 2已缴费

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getRoundingAmount() {
        return roundingAmount;
    }

    public void setRoundingAmount(float roundingAmount) {
        this.roundingAmount = roundingAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public float getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(float initialCost) {
        this.initialCost = initialCost;
    }

    public float getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(float finalCost) {
        this.finalCost = finalCost;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
