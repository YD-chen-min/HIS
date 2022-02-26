package com.yandan.model;

//检查项目
public class Project {
    private String id;//项目编号
    private String name;//项目名称
    private String deptId;//所属科室编号
    private float cost;//费用

    public Project() {
    }

    public Project(String id, String name, String deptId, float cost) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

}
