package com.yandan.service;

import com.yandan.dao.PatientInfoDao;
import com.yandan.model.PatientInfo;
import com.yandan.service.interfaces.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PatientService implements PatientServiceImp {
    @Autowired
    private PatientInfoDao patientInfoDao;
    @Override
    public List<PatientInfo> getPatientInfos(HashMap<String, Object> paramMap) {
        String patientName=(String)paramMap.get("patientName");
        String patientId=(String)paramMap.get("patientId");
        String cardId=(String)paramMap.get("cardId");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(!("undefined".equals(patientName)||"".equals(patientName)||patientName==null)){
            map.put("name",patientName);
        }
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("id",patientId);
        }
        if(!("undefined".equals(cardId)||"".equals(cardId)||cardId==null)){
            map.put("card_id",cardId);
        }
        List<PatientInfo> patientInfos= patientInfoDao.getPatientInfos(map);
        return patientInfos;
    }

    /**
     * 获取一页病人信息
     * @param paramMap
     * @return
     */
    public List<PatientInfo> getPagePatientInfo(HashMap<String, Object> paramMap) {
        String patientName=(String)paramMap.get("patientName");
        String patientId=(String)paramMap.get("patientId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(!("undefined".equals(patientName)||"".equals(patientName)||patientName==null)){
            map.put("name",patientName);
        }
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("id",patientId);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        List<PatientInfo> patientInfos= patientInfoDao.getPagePatientInfos(map);
        return patientInfos;
    }

    /**
     * 获取符合条件的病人数目
     * @param paramMap
     * @return
     */
    public int getPatientCounts(HashMap<String, Object> paramMap) {
        String patientId=(String)paramMap.get("patientId");
        String patientName=(String)paramMap.get("patientName");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(!("undefined".equals(patientName)||"".equals(patientName)||patientName==null)){
            map.put("name",patientName);
        }
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("id",patientId);
        }
        int counts= patientInfoDao.getPatientCounts(map);
        return counts;
    }
    @Override
    public int insertPatientInfo(HashMap<String, Object> paramMap) {
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        String carId=(String)paramMap.get("cardId");
        int sex=(int)paramMap.get("sex");
        int age=(int)paramMap.get("age");
        String tel=(String)paramMap.get("tel");
        String birth=(String) paramMap.get("birth");
        String insurance=(String)paramMap.get("insurance");
        String bloodType=(String)paramMap.get("bloodType");
        String medicalHistory=(String)paramMap.get("medicalHistory");
        String allergicHistory=(String)paramMap.get("allergicHistory");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("cardId",carId);
        map.put("id",id);
        map.put("name",name);
        map.put("sex",sex);
        map.put("tel",tel);
        map.put("medicalHistory",medicalHistory);
        map.put("allergicHistory",allergicHistory);
        map.put("bloodType",bloodType);
        map.put("birth",birth);
        map.put("age",age);
        return patientInfoDao.insertPatientInfo(map);
    }

    @Override
    public int updatePatientInfo(HashMap<String, Object> paramMap) {
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        String carId=(String)paramMap.get("cardId");
        int sex=(int)paramMap.get("sex");
        int age=(int)paramMap.get("age");
        String tel=(String)paramMap.get("tel");
        String birth=(String) paramMap.get("birth");
        String insurance=(String)paramMap.get("insurance");
        String bloodType=(String)paramMap.get("bloodType");
        String medicalHistory=(String)paramMap.get("medicalHistory");
        String allergicHistory=(String)paramMap.get("allergicHistory");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("card_id",carId);
        map.put("id",id);
        map.put("name",name);
        map.put("sex",sex);
        map.put("tel",tel);
        map.put("medical_history",medicalHistory);
        map.put("allergic_history",allergicHistory);
        map.put("blood_type",bloodType);
        map.put("birth",birth);
        map.put("age",age);
        return patientInfoDao.updatePatientInfo(map);
    }
}
