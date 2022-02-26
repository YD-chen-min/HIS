package com.yandan.model;

import java.util.List;

public class PageUser {
    private int total;
    private List<User> rows;

    public PageUser(int total, List<User> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageUser() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }
}
