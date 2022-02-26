package com.yandan.model;
//药品信息
public class MedicineInfo {
    private String id;//药品编号
    private String name;//药品名称
    private String specification;//规格
    private float price;//单价
    private String madeDate;//生产日期
    private String buyDate;//购入日期
    private int inventory;//库存
    private String manufacturer;//厂商
    private String remark;//备注
    private int type;//种类1中药2西药

    public MedicineInfo() {
    }

    public MedicineInfo(String id, String name, String specification, float price, String madeDate, String buyDate, int inventory, String manufacturer, String remark, int type) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.madeDate = madeDate;
        this.buyDate = buyDate;
        this.inventory = inventory;
        this.manufacturer = manufacturer;
        this.remark = remark;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
