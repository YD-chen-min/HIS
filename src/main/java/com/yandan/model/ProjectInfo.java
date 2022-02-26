package com.yandan.model;
//项目检查信息
public class ProjectInfo {
    private String id;//项目检查id
    private String projectId;//项目id
    private float initialCost;//初始金额
    private float finalCost;//金额
    private String registrationId;//挂号id
    private int status;//状态 -1已取消 0已完成 1未缴费 2已缴费
    private float roundingAmount;//舍入金额

    public ProjectInfo() {
    }

    public ProjectInfo(String id, String projectId, float initialCost, float finalCost, String registrationId, int status, float roundingAmount) {
        this.id = id;
        this.projectId = projectId;
        this.initialCost = initialCost;
        this.finalCost = finalCost;
        this.registrationId = registrationId;
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public int getPay() {
        return status;
    }

    public void setPay(int status) {
        this.status = status;
    }
}
