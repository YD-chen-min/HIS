package com.yandan.service;

import com.yandan.dao.DiagnosticInfoDao;
import com.yandan.dao.PrescriptionHisDao;
import com.yandan.dao.PrescriptionInfoDao;
import com.yandan.dao.RegistrationHisDao;
import com.yandan.model.DiagnosticInfo;
import com.yandan.model.PrescriptionHis;
import com.yandan.model.PrescriptionInfo;
import com.yandan.model.RegistrationHis;
import com.yandan.service.interfaces.PrescriptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class PrescriptionService  implements PrescriptionServiceImp {
    @Autowired
    private PrescriptionInfoDao prescriptionInfoDao;
    @Autowired
    private PrescriptionHisDao prescriptionHisDao;
    @Autowired
    private KeyBuilder keyBuilder;
    @Autowired
    private DiagnosticInfoDao diagnosticInfoDao;
    @Autowired
    private RegistrationHisDao registrationHisDao;
    @Override
    public List<PrescriptionInfo> getPrescriptionInfos(HashMap<String, Object> paramMap) {
        return null;
    }

    @Override
    public List<PrescriptionHis> getPagePrescriptionHis(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        start=(start-1)*size;
        String patientId=(String)paramMap.get("patientId");
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("patient_id",patientId);
        }
        map.put("size",size);
        map.put("start",start);
        return prescriptionHisDao.getPrescriptionHis(map);
    }
    public int getPrescriptionHisCount(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String patientId=(String)paramMap.get("patientId");
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("patient_id",patientId);
        }
        return prescriptionHisDao.getPrescriptionHisCounts(map);
    }
    @Override
    public int insertPrescription(HashMap<String, Object> paramMap) {
        int flag=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        String diagnosticId=(String)paramMap.get("diagnosticId");
        map.put("keyType",2);
        map.put("diagnosticId",diagnosticId);
        String id=keyBuilder.getKey(map);
        map.clear();
        map.put("id",diagnosticId);
        List<DiagnosticInfo> diagnosticInfos=diagnosticInfoDao.getDiagnosticInfos(map);
        map.clear();
        String registrationId=diagnosticInfos.get(0).getRegistrationId();
        String medicineIds=(String)paramMap.get("medicineIds");
        String medicineName=(String)paramMap.get("medicineNames");
        float initialCost=(float) paramMap.get("initialCost");
        int status=1;
        String invalidReason=(String)paramMap.get("invalidReason");
        String remark=(String)paramMap.get("remark");
        map.put("id",id);
        map.put("medicineIds",medicineIds);
        map.put("medicineName",medicineName);
        map.put("status",status);
        map.put("invalidReason",invalidReason);
        map.put("registrationId",registrationId);
        map.put("remark",remark);
        map.put("initialCost",initialCost);
        flag+=prescriptionInfoDao.insertPrescriptionInfo(map);
        map.clear();
        map.put("id",registrationId);
        map.put("start",0);
        map.put("size",2);
        List<RegistrationHis> registrationHis=registrationHisDao.getRegistrationHis(map);
        map.clear();
        String patientId=registrationHis.get(0).getPatientId();
        String doctorId=registrationHis.get(0).getDoctorId();
        map.put("id",id);
        map.put("medicineIds",medicineIds);
        map.put("status",status);
        map.put("invalidReason",invalidReason);
        map.put("remark",remark);
        map.put("patientId",patientId);
        map.put("doctorId",doctorId);
        map.put("initialCost",initialCost);
        flag+=prescriptionHisDao.insertPrescriptionHis(map);
        return flag;
    }
    public int updatePrescriptionInfo(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String id=(String)paramMap.get("id");
        int status=(int)paramMap.get("status");
        float roundingAmount=(float)paramMap.get("roundingAmount");
        float finalCost=(float) paramMap.get("finalCost");
        map.put("id",id);
        map.put("status",status);
        map.put("rounding_amount",roundingAmount);
        map.put("final_cost",finalCost);
        return prescriptionInfoDao.updatePrescriptionInfo(map);
    }
    public int updatePrescriptionHis(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String id=(String)paramMap.get("id");
        int status=(int)paramMap.get("status");
        float roundingAmount=(float)paramMap.get("roundingAmount");
        float finalCost=(float) paramMap.get("finalCost");
        map.put("id",id);
        map.put("status",status);
        map.put("rounding_amount",roundingAmount);
        map.put("final_cost",finalCost);
        return prescriptionHisDao.updatePrescriptionHis(map);
    }
    public int deletePrescriptionInfos(List<String> ids){
        return prescriptionInfoDao.deletePrescriptionInfo(ids);
    }
}
