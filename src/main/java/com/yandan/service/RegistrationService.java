package com.yandan.service;

import com.yandan.dao.*;
import com.yandan.model.*;
import com.yandan.service.interfaces.RegistrationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RegistrationService  implements RegistrationServiceImp {
    @Autowired
    private KeyBuilder keyBuilder;
    @Autowired
    private RegistrationHisDao registrationHisDao;
    @Autowired
    private RegistrationInfoDao registrationInfoDao;
    @Autowired
    private PatientInfoDao patientInfoDao;
    @Autowired
    private DoctorInfoDao doctorInfoDao;
    @Autowired
    private DeptInfoDao deptInfoDao;
    @Override
    public Map<String,Object> registerInfo(HashMap<String, Object> paramMap) {
        int flag=0;
        int costType=(int)paramMap.get("costType");
        int type=(int)paramMap.get("type");
        int firstOrLast=(int)paramMap.get("firstOrLast");
        String deptId=(String)paramMap.get("deptId");
        String doctorWorkId=(String)paramMap.get("doctorWorkId");
        float initialCost=0;
        if(type==1){
            initialCost=10;
        }else{
            initialCost=100;
        }
        String cardId=(String)paramMap.get("cardId");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("keyType",4);
        String id=keyBuilder.getKey(map);
        map.clear();
        map.put("work_id",doctorWorkId);
        DoctorInfo doctorInfo=doctorInfoDao.getDoctorInfos(map).get(0);
        map.clear();
        map.put("card_id",cardId);
        PatientInfo patientInfo=patientInfoDao.getPatientInfos(map).get(0);
        map.clear();
        map.put("id",id);
        map.put("cardId",cardId);
        map.put("costType",costType);
        map.put("type",type);
        map.put("deptId",deptId);
        map.put("doctorWorkId",doctorWorkId);
        map.put("firstOrLast",firstOrLast);
        map.put("status",1);
        map.put("initialCost",initialCost);
        flag+=registrationInfoDao.insertRegistrationInfo(map);
        map.put("patientId",patientInfo.getId());
        map.put("doctorId",doctorInfo.getId());
        flag+=registrationHisDao.insertRegistrationHis(map);
       map.clear();
       map.put("flag",flag);
       map.put("initialCost",initialCost);
       map.put("id",id);
       return map;
    }

    @Override
    public List<RegistrationInfo> getRegistrationInfos(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String doctorWorkId=(String)paramMap.get("doctorWorkId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        if(!("undefined".equals(doctorWorkId)||"".equals(doctorWorkId)||doctorWorkId==null)){
            map.put("doctor_work_id",doctorWorkId);
        }
        List<RegistrationInfo> registrationInfos=registrationInfoDao.getRegistrationInfos(map);
        return registrationInfos;
    }
    public int getRegistrationInfoCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String doctorWorkId=(String)paramMap.get("doctorWorkId");
        if(!("undefined".equals(doctorWorkId)||"".equals(doctorWorkId))){
            map.put("doctor_work_id",doctorWorkId);
        }
        return registrationInfoDao.getRegistrationInfoCounts(map);
    }
    public int getRegistrationHisCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String patientId=(String)paramMap.get("patientId");
        if(!("undefined".equals(patientId)||"".equals(patientId))){
            map.put("patient_id",patientId);
        }
        return registrationHisDao.getRegistrationHisCounts(map);
    }
    @Override
    public List<RegistrationHis> getRegistrationHis(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String patientId=(String)paramMap.get("patientId");
        String patientName=(String)paramMap.get("patientName");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("patient_id",patientId);
        }
        if(!("undefined".equals(patientName)||"".equals(patientName)||patientName==null)){
            map.put("patient_name",patientName);
        }
        List<RegistrationHis> registrationHis=registrationHisDao.getRegistrationHis(map);
        return registrationHis;
    }

    @Override
    public int updateRegistrationInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        int flag=0;
        String id=(String)paramMap.get("id");
        float initialCost=(float)paramMap.get("initialCost");
        float roundingAmount=(float)paramMap.get("roundingAmount");
        float finalCost=(float)paramMap.get("finalCost");
        map.put("status",2);
        map.put("id",id);
        map.put("rounding_amount",roundingAmount);
        map.put("initial_cost",initialCost);
        map.put("final_cost",finalCost);
        flag+=registrationInfoDao.updateRegistrationInfo(map);
        flag+=registrationHisDao.updateRegistrationHis(map);
        return flag;
    }

    @Override
    public int deleteRegistrationInfo(List<String> ids) {
        return 0;
    }

    /**
     * 获取具体挂号单信息用于诊断页面信息回显
     * @param registrationId
     * @return
     */
    public Map<String,Object> getInfo(String registrationId){
        HashMap<String ,Object> map=new HashMap<String ,Object>();
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("start",0);
        paramMap.put("size",2);
        paramMap.put("id",registrationId);
        List<RegistrationHis> registrationHis=registrationHisDao.getRegistrationHis(paramMap);
        String patientId=registrationHis.get(0).getPatientId();
        String doctorId=registrationHis.get(0).getDoctorId();
        String deptId=registrationHis.get(0).getDeptId();
        paramMap.clear();
        paramMap.put("id",doctorId);
        List<DoctorInfo> doctorInfos=doctorInfoDao.getDoctorInfos(paramMap);
        paramMap.put("id",patientId);
        List<PatientInfo> patientInfos=patientInfoDao.getPatientInfos(paramMap);
        paramMap.put("id",deptId);
        List<DeptInfo> deptInfos=deptInfoDao.getDeptInfos(paramMap);
        map.put("doctorInfo",doctorInfos.get(0));
        map.put("deptInfo",deptInfos.get(0));
        map.put("patientInfo",patientInfos.get(0));
        return map;
    }
}
