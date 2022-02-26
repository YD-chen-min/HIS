package com.yandan.model;
//用户信息
public class User {
    private  String name;//名称
    private String id;//账号
    private String password;//密码
    private String roleId;//角色id
    private String role;//角色名称
    private String workId;//工号
    public User() {
    }

    public User(String name, String id, String password, String roleId, String role,String workId) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.roleId = roleId;
        this.role = role;
        this.workId=workId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
