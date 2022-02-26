package com.yandan.controller;

import com.yandan.model.DoctorInfo;
import com.yandan.model.PatientInfo;
import com.yandan.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctor/get")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public List<DoctorInfo> getDoctorInfos(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        String type=request.getParameter("type");
        paramMap.put("workId",request.getParameter("workId"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("job",request.getParameter("job"));
        if("1".equals(type)){
            paramMap.put("job","医生");
        }
        if("2".equals(type)){
            paramMap.put("job","专家");
        }
        return doctorService.getDoctorInfos(paramMap);
    }
    @RequestMapping("/doctor/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageDoctorInfos(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("workId",request.getParameter("workId"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("job",request.getParameter("job"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<DoctorInfo> doctorInfos=doctorService.getPageDoctorInfos(paramMap);
        int counts=doctorService.getDoctorInfoCounts(paramMap);
        map.put("rows",doctorInfos);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/doctor/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertDoctorInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        int sex=Integer.parseInt(request.getParameter("sex"));
        paramMap.put("workId",request.getParameter("workId"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("job",request.getParameter("job"));
        paramMap.put("joinDate",request.getParameter("p1"));
        paramMap.put("remark",request.getParameter("remark"));
        paramMap.put("sex",sex);
        int flag=0;
        flag=doctorService.insertDoctorInfo(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败!");
        }
        return map;
    }
    @RequestMapping("/doctor/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateDoctorInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        int sex=Integer.parseInt(request.getParameter("sex"));
        paramMap.put("workId",request.getParameter("workId"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("job",request.getParameter("job"));
        paramMap.put("joinDate",request.getParameter("p1"));
        paramMap.put("remark",request.getParameter("remark"));
        paramMap.put("sex",sex);
        int flag=0;
        flag=doctorService.updateDoctorInfo(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }
    @RequestMapping("/doctor/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteDeptInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String wordIdsString=request.getParameter("wordIds");
        List<String> workIds= Arrays.asList( wordIdsString.split(","));
        int num1=workIds.size();
        int num2=0;
        num2=doctorService.deleteDoctorInfo(workIds);
        map.put("msg",num1+"条记录中成功删除了"+num2+"条记录");
        return map;
    }
    @RequestMapping(value = "/doctor/get/{workId}")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPatientInfo(@PathVariable("workId") String workId){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("workId",workId);
        List<DoctorInfo> doctorInfos=doctorService.getDoctorInfos(paramMap);
        map.put("doctorInfos",doctorInfos);
        return  map;
    }
}
