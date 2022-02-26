package com.yandan.model;
//医生信息
public class DoctorInfo {
    private String workId;//医生工号
    private String id;//医生身份证
    private String name;//姓名
    private int sex;//性别 0男1女
    private String job;//职位（专家，医生，前台护士）
    private String deptId;//所属部门编号
    private String joinDate;//入职日期
    private String remark;//备注可填写医生荣誉等等

    public DoctorInfo() {
    }

    public DoctorInfo(String workId, String id, String name, int sex, String job, String deptId, String joinDate, String remark) {
        this.workId = workId;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.job = job;
        this.deptId = deptId;
        this.joinDate = joinDate;
        this.remark = remark;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
