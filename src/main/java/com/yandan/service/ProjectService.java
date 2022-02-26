package com.yandan.service;

import com.yandan.dao.ProjectDao;
import com.yandan.dao.ProjectHisDao;
import com.yandan.dao.ProjectInfoDao;
import com.yandan.dao.RegistrationHisDao;
import com.yandan.model.Project;
import com.yandan.model.ProjectHis;
import com.yandan.model.ProjectInfo;
import com.yandan.model.RegistrationHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class ProjectService{
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectHisDao projectHisDao;
    @Autowired
    private KeyBuilder keyBuilder;
    @Autowired
    private RegistrationHisDao registrationHisDao;
   public List<Project> getPageProjects(HashMap<String,Object> paramMap){
       HashMap<String,Object> map=new HashMap<String, Object>();
       String deptId=(String)paramMap.get("deptId");
       int size=(int)paramMap.get("size");
       int start=(int)paramMap.get("start");
       if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
           map.put("dept_id",deptId);
       }
       start=(start-1)*size;
       map.put("start",start);
       map.put("size",size);
       return projectDao.getPageProjects(map);
   }
   public int getProjectCounts(HashMap<String,Object> paramMap){
       HashMap<String,Object> map=new HashMap<String, Object>();
       String deptId=(String)paramMap.get("deptId");
       if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
           map.put("dept_id",deptId);
       }
       return projectDao.getProjectCounts(map);
   }
    public int insertProject(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String deptId=(String)paramMap.get("deptId");
        float cost=(float)paramMap.get("cost");
        map.put("id",id);
        map.put("name",name);
        map.put("deptId",deptId);
        map.put("cost",cost);
        return projectDao.insertProject(map);
    }
    public int updateProject(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=(String)paramMap.get("id");
        String name=(String)paramMap.get("name");
        String deptId=(String)paramMap.get("deptId");
        float cost=(float)paramMap.get("cost");
        map.put("id",id);
        map.put("name",name);
        map.put("dept_id",deptId);
        map.put("cost",cost);
        return projectDao.updateProject(map);
    }
    public int deleteProjects(List<String> ids){
       return projectDao.deleteProjects(ids);
    }
    public List<ProjectInfo> getPageProjectInfos(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String registrationId=(String)paramMap.get("registrationId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(registrationId)||"".equals(registrationId)||registrationId==null)){
            map.put("registration_id",registrationId);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return projectInfoDao.getPageProjectInfos(map);
    }
    public int getProjectInfoCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String registrationId=(String)paramMap.get("registrationId");
        if(!("undefined".equals(registrationId)||"".equals(registrationId)||registrationId==null)){
            map.put("registration_id",registrationId);
        }
        return projectInfoDao.getProjectInfoCounts(map);
    }
    public int insertProjectInfo(HashMap<String,Object> paramMap){
       int flag=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        String diagnosticId=(String)paramMap.get("diagnosticId");
        map.put("keyType",3);
        map.put("diagnosticId",diagnosticId);
        String id=keyBuilder.getKey(map);
        map.clear();
        String projectId=(String)paramMap.get("projectId");
        float initialCost=(float)paramMap.get("initialCost");
        String registrationId=(String)paramMap.get("registrationId");
        map.put("id",registrationId);
        map.put("start",0);
        map.put("size",1);
        RegistrationHis registrationHis=registrationHisDao.getRegistrationHis(map).get(0);
        map.clear();
        int status=1;
        map.put("status",status);
        map.put("id",id);
        map.put("projectId",projectId);
        map.put("initialCost",initialCost);
        map.put("registrationId",registrationId);
        flag= projectInfoDao.insertProjectInfo(map);
        map.put("patientId",registrationHis.getPatientId());
        map.put("doctorId",registrationHis.getDoctorId());
        flag=projectHisDao.insertProjectHis(map);
        return flag;
    }
    public int updateProjectInfo(HashMap<String,Object> paramMap){
       HashMap<String,Object> map=new HashMap<String,Object>();
       if(paramMap.get("finalCost")!=null){
           map.put("final_cost",(float)paramMap.get("finalCost"));
       }
        if(paramMap.get("roundingAmount")!=null){
            map.put("rounding_amount",(float)paramMap.get("roundingAmount"));
        }
        if(paramMap.get("status")!=null){
            map.put("status",(int)paramMap.get("status"));
        }
        map.put("id",(String)paramMap.get("id"));
        return projectInfoDao.updateProjectInfo(map);
    }
    public int deleteProjectInfos(List<String> ids){
        return projectInfoDao.deleteProjectInfos(ids);
    }

    public List<ProjectHis> getPageProjectHis(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String patientId=(String)paramMap.get("patientId");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(patientId)||"".equals(patientId)||patientId==null)){
            map.put("patient_id",patientId);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return projectHisDao.getPageProjectHis(map);
    }
    public int getProjectHisCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String, Object>();
        String deptId=(String)paramMap.get("deptId");
        if(!("undefined".equals(deptId)||"".equals(deptId)||deptId==null)){
            map.put("dept_id",deptId);
        }
        return projectHisDao.getProjectHisCounts(map);
    }

    public int updateProjectHis(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(paramMap.get("finalCost")!=null){
            map.put("final_cost",(float)paramMap.get("finalCost"));
        }
        if(paramMap.get("roundingAmount")!=null){
            map.put("rounding_amount",(float)paramMap.get("roundingAmount"));
        }
        if(paramMap.get("status")!=null){
            map.put("status",(int)paramMap.get("status"));
        }
        map.put("id",(String)paramMap.get("id"));
        return projectHisDao.updateProjectHis(map);
    }
}
