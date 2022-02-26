package com.yandan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//easyUI tree所需结点数据结构
public class Node {
    private int id;
    private String text;
    private String state;
    private boolean checked;
    private String iconCls;
    private Map<String,Object> attributes=new HashMap<String,Object>();
    private List<Node> children;
    private  int pid;



    public Node(int id, String text, String state, boolean checked, String iconCls, Map<String, Object> attributes, List<Node> children,int pid) {
        this.id = id;
        this.text = text;
        this.state = state;
        this.checked = checked;
        this.iconCls = iconCls;
        this.attributes = attributes;
        this.children = children;
        this.pid=pid;
    }
    public Node(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
