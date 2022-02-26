package com.yandan.model;
//处方信息记录
public class PrescriptionHis {
    private String id;//处方id
    private String medicineIds;//药品编号
    private int status;//状态 -1已取消 0已完成 1未缴费 2已缴费
    private String invalidReason;//作废原因
    private String remark;//医嘱
    private String doctorId;//医生身份证
    private String patientId;//病人身份证
    private float initialCost;//初始金额
    private float roundingAmount;//舍入金额
    private float finalCost;//最终金额

    public PrescriptionHis(String id) {
        this.id = id;
    }

    public PrescriptionHis(String id, String medicineIds, int status, String invalidReason, String remark, String doctorId, String patientId, float initialCost, float roundingAmount, float finalCost) {
        this.id = id;
        this.medicineIds = medicineIds;
        this.status = status;
        this.invalidReason = invalidReason;
        this.remark = remark;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.initialCost = initialCost;
        this.roundingAmount = roundingAmount;
        this.finalCost = finalCost;
    }

    public float getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(float initialCost) {
        this.initialCost = initialCost;
    }

    public float getRoundingAmount() {
        return roundingAmount;
    }

    public void setRoundingAmount(float roundingAmount) {
        this.roundingAmount = roundingAmount;
    }

    public float getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(float finalCost) {
        this.finalCost = finalCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineIds() {
        return medicineIds;
    }

    public void setMedicineIds(String medicineIds) {
        this.medicineIds = medicineIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
