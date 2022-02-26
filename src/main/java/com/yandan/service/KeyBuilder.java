package com.yandan.service;

import com.yandan.dao.DiagnosticHisDao;
import com.yandan.dao.PrescriptionHisDao;
import com.yandan.dao.ProjectHisDao;
import com.yandan.dao.RegistrationHisDao;
import com.yandan.service.interfaces.KeyBuilderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
@Repository
public class KeyBuilder implements KeyBuilderImp {
    @Autowired
    private DiagnosticHisDao diagnosticHisDao;
    @Autowired
    private PrescriptionHisDao prescriptionHisDao;
    @Autowired
    private ProjectHisDao projectHisDao;
    @Autowired
    private RegistrationHisDao registrationHisDao;
    @Override
    public String getKey(HashMap<String, Object> paramMap) {
        int keyType=(int)paramMap.get("keyType");
        String key=null;
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateString=sdf.format(date);
        String []dateStrings=dateString.split("-");
        String year=dateStrings[0];
        String moth=dateStrings[1];
        String day=dateStrings[2];
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(keyType==1){
            String doctorWorkId=(String)paramMap.get("doctorWorkId");
           do{
               int no= new Random().nextInt(100);
               key=year.substring(2,year.length())+moth+day+doctorWorkId+no;
               map.put("id",key);
           } while (diagnosticHisDao.getDiagnosticHisCounts(map)!=0);
            return key;
        }
        if (keyType==2){
            String diagnosticId=(String)paramMap.get("diagnosticId");
          do{
              int no=new Random().nextInt(10);
              key=diagnosticId+no;
              map.put("id",key);
          }while (prescriptionHisDao.getPrescriptionHisCounts(map)!=0);
            return key;
        }
        if (keyType==3){
            String diagnosticId=(String)paramMap.get("diagnosticId");
            do{
                int no=new Random().nextInt(10);
                key=diagnosticId+no;
                map.put("id",key);
            }while (projectHisDao.getProjectHisCounts(map)!=0);
            return key;
        }
        if(keyType==4){
           do{
               int no1=new Random().nextInt(10000);
               int no2=new Random().nextInt(10000);
               key=year.substring(2,year.length())+moth+day+no1+no2;
               map.put("id",key);
           }while (registrationHisDao.getRegistrationHisCounts(map)!=0);
            return key;
        }
        return key;
    }
}
