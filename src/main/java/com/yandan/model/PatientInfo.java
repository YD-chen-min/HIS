package com.yandan.model;

//病人信息
public class PatientInfo {
    private String cardId;//卡号
    private String id;//病人身份证
    private String name;//姓名
    private int sex;//性别0男1女
    private int age;//年龄
    private String tel;//联系电话
    private String medicalHistory;//病史
    private String allergicHistory;//过敏史
    private String bloodType;//血型
    private String birth;//生日

    public PatientInfo() {
    }

    public PatientInfo(String cardId, String id, String name, int sex, int age, String tel, String medicalHistory, String allergicHistory, String bloodType, String birth) {
        this.cardId = cardId;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.medicalHistory = medicalHistory;
        this.allergicHistory = allergicHistory;
        this.bloodType = bloodType;
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
