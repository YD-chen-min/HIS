package com.yandan.model;

public class DeptInfo {
    private String id;//科室编号（4位数字组成，前两位表示科，后两位表示室）
    private String name;//科室名字
    private String remark;//科室备注

    public DeptInfo() {
    }

    public DeptInfo(String id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
