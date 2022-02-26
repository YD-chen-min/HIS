package com.yandan.model;
//处方信息
public class PrescriptionInfo {
    private String id;//处方id
    private String medicineIds;//药品编号
    private String medicineName;//药品名称
    private int status;//状态 -1已取消 0已完成 1未缴费 2已缴费
    private String invalidReason;//作废原因
    private String registrationId;//挂号id
    private String remark;//医嘱
    private float initialCost;//初始金额
    private float roundingAmount;//舍入金额
    private float finalCost;//最终金额

    public PrescriptionInfo() {
    }

    public PrescriptionInfo(String id, String medicineIds, String medicineName, int status, String invalidReason, String registrationId, String remark,  float initialCost, float roundingAmount, float finalCost) {
        this.id = id;
        this.medicineIds = medicineIds;
        this.medicineName = medicineName;
        this.status = status;
        this.invalidReason = invalidReason;
        this.registrationId = registrationId;
        this.remark = remark;
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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
