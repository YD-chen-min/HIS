package com.yandan.service;

import com.yandan.dao.MedicineInfoDao;
import com.yandan.model.MedicineInfo;
import com.yandan.model.PrescriptionInfo;
import com.yandan.service.interfaces.MedicineServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class MedicineService  implements MedicineServiceImp {
    @Autowired
    private MedicineInfoDao medicineInfoDao;
    @Override
    public List<MedicineInfo> getMedicineInfos(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String typeString=(String)paramMap.get("type");
        if(!("undefined".equals(id)||"".equals(id))){
            map.put("id",id);
        }
        if(!("undefined".equals(name)||"".equals(name))){
            map.put("name",name);
        }
        if(!("undefined".equals(typeString)||"".equals(typeString))){
           int type=Integer.parseInt(typeString);
           map.put("type",type);
        }
        return medicineInfoDao.getMedicineInfos(map);
    }

    @Override
    public int insertMedicineInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        int type=(int)paramMap.get("type");
        String specification=(String)paramMap.get("specification");
        float price=(float) paramMap.get("price");
        String madeDate=(String) paramMap.get("madeDate");
        String buyDate=(String) paramMap.get("buyDate");
       String inventoryStr=(String)paramMap.get("inventory");
        int inventory=Integer.parseInt(inventoryStr);
        String manufacturer=(String)paramMap.get("manufacturer");
        String remark=(String)paramMap.get("remark");
        map.put("id",id);
        map.put("name",name);
        map.put("type",type);
        map.put("specification",specification);
        map.put("price",price);
        map.put("inventory",inventory);
        map.put("manufacturer",manufacturer);
        map.put("remark",remark);
        map.put("madeDate",madeDate);
        map.put("buyDate",buyDate);

        return medicineInfoDao.insertMedicineInfo(paramMap);
    }

    @Override
    public int updateMedicineInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        int type=(int)paramMap.get("type");
        String specification=(String)paramMap.get("specification");
        float price=(float) paramMap.get("price");
        String madeDate=(String) paramMap.get("madeDate");
        String buyDate=(String) paramMap.get("buyDate");
        String inventoryStr=(String)paramMap.get("inventory");
        int inventory=Integer.parseInt(inventoryStr);
        String manufacturer=(String)paramMap.get("manufacturer");
        String remark=(String)paramMap.get("remark");
        map.put("id",id);
        map.put("name",name);
        map.put("type",type);
        map.put("specification",specification);
        map.put("price",price);
        map.put("inventory",inventory);
        map.put("manufacturer",manufacturer);
        map.put("remark",remark);
        map.put("made_date",madeDate);
        map.put("buy_date",buyDate);
        return medicineInfoDao.updateMedicineInfo(paramMap);
    }

    @Override
    public int deleteMedicineInfo(List<String> Ids) {
        return medicineInfoDao.deleteMedicineInfo(Ids);
    }

    @Override
    public int updateMedicineInfoByPrescription(PrescriptionInfo prescriptionInfo) {
        return 0;
    }
    public int getMedicineInfoCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String typeString=(String)paramMap.get("type");
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(typeString)||"".equals(typeString)||typeString==null)){
            int type=Integer.parseInt(typeString);
            map.put("type",type);
        }
        return medicineInfoDao.getMedicineInfoCounts(map);
    }
    public List<MedicineInfo> getPageMedicineInfos(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String typeString=(String)paramMap.get("type");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(typeString)||"".equals(typeString)||typeString==null)){
            int type=Integer.parseInt(typeString);
            map.put("type",type);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return medicineInfoDao.getPageMedicineInfos(map);
    }
    public List<MedicineInfo> getMedicineInfo(HashMap<String, Object> paramMap) {

        return medicineInfoDao.getMedicineInfos(paramMap);
    }
}
