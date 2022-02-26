package com.yandan.model;
//挂号记录
public class RegistrationHis {
    private String id;//挂号id
    private String patientId;//病人身份证
    private int costType;//费别1自费2公费
    private int type;//种类 1普通号2专家号
    private String deptId;//所挂科室编号
    private String doctorId;//所挂医生身份证
    private float initialCost;//初始金额
    private float finalCost;//最终金额
    private int firstOrLast;//初复诊1初诊2复诊
    private int status;//状态 -1已取消 0已完成 1未缴费 2已缴费
    private float roundingAmount;//舍入金额

    public RegistrationHis() {
    }

    public RegistrationHis(String id, String patientId, int costType, int type, String deptId, String doctorId, float initialCost, float finalCost, int firstOrLast, int status, float roundingAmount) {
        this.id = id;
        this.patientId = patientId;
        this.costType = costType;
        this.type = type;
        this.deptId = deptId;
        this.doctorId = doctorId;
        this.initialCost = initialCost;
        this.finalCost = finalCost;
        this.firstOrLast = firstOrLast;
        this.status = status;
        this.roundingAmount = roundingAmount;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getCostType() {
        return costType;
    }

    public void setCostType(int costType) {
        this.costType = costType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public int getFirstOrLast() {
        return firstOrLast;
    }

    public void setFirstOrLast(int firstOrLast) {
        this.firstOrLast = firstOrLast;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
