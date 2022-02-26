package com.yandan.controller;

import com.yandan.model.PatientInfo;
import com.yandan.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/patient/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPatientInfoPage(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("patientId",request.getParameter("id"));
        paramMap.put("patientName",request.getParameter("name"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<PatientInfo> patientInfos=patientService.getPagePatientInfo(paramMap);
        int counts=patientService.getPatientCounts(paramMap);
        map.put("rows",patientInfos);
        map.put("total",counts);
        return  map;
    }
    @RequestMapping(value = "/patient/get")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPatientInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("patientId",request.getParameter("patientId"));
        paramMap.put("patientName",request.getParameter("patientName"));
        paramMap.put("cardId",request.getParameter("cardId"));
        List<PatientInfo> patientInfos=patientService.getPatientInfos(paramMap);
        int counts=patientService.getPatientCounts(paramMap);
        map.put("patients",patientInfos);
        map.put("counts",counts);
        return  map;
    }
    @RequestMapping(value = "/patient/get/{id}")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPatientInfo(@PathVariable("id") String id){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
       paramMap.put("patientId",id);
       List<PatientInfo> patientInfos=patientService.getPatientInfos(paramMap);
       map.put("patientInfos",patientInfos);
        return  map;
    }
    @RequestMapping(value = "/patient/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertPatientInfo(HttpServletRequest request){
        int flag=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("sex",Integer.parseInt(request.getParameter("sex")));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("cardId",request.getParameter("cardId"));
        paramMap.put("age",Integer.parseInt(request.getParameter("age")));
        paramMap.put("tel",request.getParameter("tel"));
        paramMap.put("birth",request.getParameter("p1"));
        paramMap.put("insurance",request.getParameter("insurance"));
        paramMap.put("bloodType",request.getParameter("bloodType"));
        paramMap.put("medicalHistory",request.getParameter("medicalHistory"));
        paramMap.put("allergicHistory",request.getParameter("allergicHistory"));
       flag= patientService.insertPatientInfo(paramMap);
       if(flag>0){
           map.put("msg","添加成功！");
       }else{
           map.put("msg","添加失败!");
       }
        return  map;
    }

    @RequestMapping(value = "/patient/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updatePatientInfo(HttpServletRequest request){
        int flag=0;
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("sex",Integer.parseInt(request.getParameter("sex")));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("cardId",request.getParameter("cardId"));
        paramMap.put("age",Integer.parseInt(request.getParameter("age")));
        paramMap.put("tel",request.getParameter("tel"));
        paramMap.put("birth",request.getParameter("p1"));
        paramMap.put("insurance",request.getParameter("insurance"));
        paramMap.put("bloodType",request.getParameter("bloodType"));
        paramMap.put("medicalHistory",request.getParameter("medicalHistory"));
        paramMap.put("allergicHistory",request.getParameter("allergicHistory"));
        flag= patientService.updatePatientInfo(paramMap);
        if(flag>0){
            map.put("msg","修改成功！");
        }else{
            map.put("msg","修改失败!");
        }
        return  map;
    }
}
