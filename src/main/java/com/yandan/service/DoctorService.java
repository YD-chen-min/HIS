package com.yandan.service;

import com.yandan.dao.DoctorInfoDao;
import com.yandan.model.DoctorInfo;
import com.yandan.model.RegistrationHis;
import com.yandan.service.interfaces.DoctorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class DoctorService implements DoctorServiceImp {

    @Autowired
    private DoctorInfoDao doctorInfoDao;
    @Override
    public List<DoctorInfo> getDoctorInfos(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String workId=(String)paramMap.get("workId");
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String deptId=(String)paramMap.get("deptId");
        String job=(String)paramMap.get("job");
        if(!("undefined".equals(workId)||"".equals(workId)||workId==null)){
            map.put("work_id",workId);
        }
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
            map.put("dept_id",deptId);
        }
        if(!("undefined".equals(job)||"".equals(job)||job==null)){
            map.put("job",job);
        }

        return doctorInfoDao.getDoctorInfos(map);
    }

    @Override
    public int insertDoctorInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String workId=(String)paramMap.get("workId");
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String job=(String)paramMap.get("job");
        String deptId=(String)paramMap.get("deptId");
        String joinDate=(String) paramMap.get("joinDate");
        String remark=(String)paramMap.get("remark");
        int sex=(int)paramMap.get("sex");
        map.put("workId",workId);
        map.put("id",id);
        map.put("name",name);
        map.put("job",job);
        map.put("deptId",deptId);
        map.put("joinDate",joinDate);
        map.put("remark",remark);
        map.put("sex",sex);
        return doctorInfoDao.insertDoctorInfo(map);
    }

    @Override
    public int updateDoctorInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String workId=(String)paramMap.get("workId");
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String job=(String)paramMap.get("job");
        String deptId=(String)paramMap.get("deptId");
        String joinDate=(String) paramMap.get("joinDate");
        String remark=(String)paramMap.get("remark");
        int sex=(int)paramMap.get("sex");
        map.put("work_id",workId);
        map.put("id",id);
        map.put("name",name);
        map.put("job",job);
        map.put("dept_id",deptId);
        map.put("join_date",joinDate);
        map.put("remark",remark);
        map.put("sex",sex);
        return doctorInfoDao.updateDoctorInfo(map);
    }

    @Override
    public int deleteDoctorInfo(List<String> workIds) {
        return doctorInfoDao.deleteDoctorInfo(workIds);
    }

    public List<DoctorInfo> getPageDoctorInfos(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String workId=(String)paramMap.get("workId");
        String name=(String)paramMap.get("name");
        String job=(String)paramMap.get("job");
        String deptId=(String)paramMap.get("deptId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(workId)||"".equals(workId)||workId==null)){
            map.put("work_id",workId);
        }

        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
            map.put("dept_id",deptId);
        }
        if(!("undefined".equals(job)||"".equals(job)||job==null)){
            map.put("job",job);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        List<DoctorInfo> doctorInfos=doctorInfoDao.getPageDoctorInfos(map);
        return doctorInfos;
    }
    public int getDoctorInfoCounts(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String workId=(String)paramMap.get("workId");
        String name=(String)paramMap.get("name");
        String job=(String)paramMap.get("job");
        String deptId=(String)paramMap.get("deptId");
        if(!("undefined".equals(workId)||"".equals(workId)||workId==null)){
            map.put("work_id",workId);
        }

        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
            map.put("dept_id",deptId);
        }
        if(!("undefined".equals(job)||"".equals(job)||job==null)){
            map.put("job",job);
        }
        return doctorInfoDao.getDoctorInfoCounts(map);
    }
}
