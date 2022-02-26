package com.yandan.service;

import com.yandan.dao.DiagnosticHisDao;
import com.yandan.dao.DiagnosticInfoDao;
import com.yandan.dao.RegistrationHisDao;
import com.yandan.model.DiagnosticHis;
import com.yandan.model.DiagnosticInfo;
import com.yandan.model.RegistrationHis;
import com.yandan.service.interfaces.DiagnosticServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiagnosticService implements DiagnosticServiceImp {
    @Autowired
    private KeyBuilder keyBuilder;
    @Autowired
    private DiagnosticInfoDao diagnosticInfoDao;
    @Autowired
    private DiagnosticHisDao diagnosticHisDao;
    @Autowired
    private RegistrationHisDao registrationHisDao;
    @Override
    public Map<String, Object> insertDiagnostic(HashMap<String, Object> paramMap) {
        int flag=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        String doctorWorkId=(String)paramMap.get("doctorWorkId");
        map.put("doctorWorkId",doctorWorkId);
        map.put("keyType",1);
        String id=keyBuilder.getKey(map);
        String registrationId=(String)paramMap.get("registrationId");
        String selfReported=(String)paramMap.get("selfReported");
        String accidentDate=(String) paramMap.get("accidentDate");
        String conclusion=(String)paramMap.get("conclusion");
        String remark=(String)paramMap.get("remark");
        Date treatmentDateStr=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
        String treatmentDate=sdf.format(treatmentDateStr);
        map.put("id",id);
        map.put("registrationId",registrationId);
        map.put("selfReported",selfReported);
        map.put("accidentDate",accidentDate);
        map.put("conclusion",conclusion);
        map.put("remark",remark);
        map.put("treatmentDate",treatmentDate);
        flag+=diagnosticInfoDao.insertDiagnosticInfo(map);
        map.clear();
        map.put("id",registrationId);
        map.put("start",0);
        map.put("size",2);
        List<RegistrationHis> registrationHis=registrationHisDao.getRegistrationHis(map);
       map.clear();
        map.put("id",id);
        map.put("patientId",registrationHis.get(0).getPatientId());
        map.put("selfReported",selfReported);
        map.put("accidentDate",accidentDate);
        map.put("conclusion",conclusion);
        map.put("remark",remark);
        map.put("treatmentDate",treatmentDate);
        map.put("doctorId",registrationHis.get(0).getDoctorId());
        flag+=diagnosticHisDao.insertDiagnosticHis(map);
        map.clear();
        map.put("flag",flag);
        map.put("diagnosticId",id);
        return map;
    }

    @Override
    public List<DiagnosticInfo> getDiagnosticInfos(HashMap<String, Object> paramMap) {
        return null;
    }

    @Override
    public List<DiagnosticHis> getDiagnosticHis(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String patientId=(String)paramMap.get("patientId");
        String id=(String)paramMap.get("id");
        String doctorId=(String)paramMap.get("doctorId");
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("patient_id",patientId);
        }
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        if(!("undefined".equals(doctorId)||"".equals(doctorId)||doctorId==null)){
            map.put("doctor_id",doctorId);
        }
        return diagnosticHisDao.getDiagnosticHis(map);
    }

    @Override
    public int deleteDiagnosticInfos(List<String> ids) {
        return diagnosticInfoDao.deleteDiagnosticInfos(ids);
    }
    public List<DiagnosticHis> getPageDiagnosticHis(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String patientId=(String)paramMap.get("patientId");
        String id=(String)paramMap.get("id");
        String doctorId=(String)paramMap.get("doctorId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(patientId)||"".equals(patientId))){
            map.put("patient_id",patientId);
        }
        if(!("undefined".equals(id)||"".equals(id))){
            map.put("id",id);
        }
        if(!("undefined".equals(doctorId)||"".equals(doctorId))){
            map.put("doctor_id",doctorId);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return diagnosticHisDao.getDiagnosticHis(map);
    }
//    public List<DiagnosticInfo> getPageDiagnosticHis(HashMap<String, Object> paramMap) {
//        HashMap<String,Object> map=new HashMap<String,Object>();
//        String patientId=(String)paramMap.get("patientId");
//        String id=(String)paramMap.get("id");
//        String doctorId=(String)paramMap.get("doctorId");
//        int start=(int)paramMap.get("start");
//        int size=(int)paramMap.get("size");
//        if(!("undefined".equals(patientId)||"".equals(patientId))){
//            map.put("patient_id",patientId);
//        }
//        if(!("undefined".equals(id)||"".equals(id))){
//            map.put("id",id);
//        }
//        if(!("undefined".equals(doctorId)||"".equals(doctorId))){
//            map.put("doctor_id",doctorId);
//        }
//        map.put("start",start);
//        map.put("size",size);
//        return diagnosticHisDao.getPageDiagnosticHis(map);
//    }
//    public int getPageDiagnosticInfoCounts(HashMap<String, Object> paramMap) {
//        HashMap<String,Object> map=new HashMap<String,Object>();
//        String patientId=(String)paramMap.get("patientId");
//        String id=(String)paramMap.get("id");
//        String doctorId=(String)paramMap.get("doctorId");
//        if(!("undefined".equals(patientId)||"".equals(patientId))){
//            map.put("patient_id",patientId);
//        }
//        if(!("undefined".equals(id)||"".equals(id))){
//            map.put("id",id);
//        }
//        if(!("undefined".equals(doctorId)||"".equals(doctorId))){
//            map.put("doctor_id",doctorId);
//        }
//        return diagnosticInfoDao.getDiagnosticInfoCounts(map);
//    }
    public int getPageDiagnosticHisCounts(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String patientId=(String)paramMap.get("patientId");
        String id=(String)paramMap.get("id");
        String doctorId=(String)paramMap.get("doctorId");
        if(!("undefined".equals(patientId)||"".equals(patientId))){
            map.put("patient_id",patientId);
        }
        if(!("undefined".equals(id)||"".equals(id))){
            map.put("id",id);
        }
        if(!("undefined".equals(doctorId)||"".equals(doctorId))){
            map.put("doctor_id",doctorId);
        }
        return diagnosticHisDao.getDiagnosticHisCounts(map);
    }
}
