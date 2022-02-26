package com.yandan.controller;


import com.yandan.model.*;
import com.yandan.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/registrationInfo/get")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getRegistrationInfoPage(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("doctorWorkId",request.getParameter("doctorWorkId"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<RegistrationInfo> registrationInfos=registrationService.getRegistrationInfos(paramMap);
        int counts=registrationService.getRegistrationInfoCounts(paramMap);
        map.put("rows",registrationInfos);
        map.put("total",counts);
        return  map;
    }
    @RequestMapping(value = "/registrationInfo/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertRegistration(HttpServletRequest request){
        int flag=0;
        Map<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        int costType=Integer.parseInt(request.getParameter("costType"));
        int firstOrLast=Integer.parseInt(request.getParameter("firstOrLast"));
        int type=Integer.parseInt(request.getParameter("type"));
        String deptId=request.getParameter("deptId");
        String doctorWorkId=request.getParameter("doctorWorkId");
        String cardId=request.getParameter("cardId");
        paramMap.put("costType",costType);
        paramMap.put("firstOrLast",firstOrLast);
        paramMap.put("type",type);
        paramMap.put("deptId",deptId);
        paramMap.put("doctorWorkId",doctorWorkId);
        paramMap.put("cardId",cardId);
        map=registrationService.registerInfo(paramMap);
        flag=(int)map.get("flag");
        if(flag>0){
            map.put("msg","请进行缴费！");
        }else {
            map.put("msg","信息出错！");
        }
        return  map;
    }
    @RequestMapping(value = "/registrationHis/get")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getRegistrationHisPage(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("patientId",request.getParameter("patientId"));
        paramMap.put("patientName",request.getParameter("patientName"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<RegistrationHis> registrationHis=registrationService.getRegistrationHis(paramMap);
        int counts=registrationService.getRegistrationHisCounts(paramMap);
        map.put("rows",registrationHis);
        map.put("total",counts);
        return  map;
    }
    @RequestMapping(value = "/registration/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateRegistrationInfo(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        String id=request.getParameter("p2");
        float initialCost=Float.parseFloat(request.getParameter("initialCost"));
        String roundingAmountStr=request.getParameter("roundingAmount");
        float roundingAmount;
        if(("undefined".equals(roundingAmountStr)||"".equals(roundingAmountStr))){
            roundingAmount=0;
        }else{
            roundingAmount=Float.parseFloat(roundingAmountStr);
        }
        float finalCost=Float.parseFloat(request.getParameter("p1"));
        paramMap.put("id",id);
        paramMap.put("initialCost",initialCost);
        paramMap.put("roundingAmount",roundingAmount);
        paramMap.put("finalCost",finalCost);
        int flag=0;
        flag=registrationService.updateRegistrationInfo(paramMap);
        if(flag>0){
            map.put("msg","结算成功!");
        }else{
            map.put("msg","结算失败!，请联系管理员进行数据检查");
        }
        return  map;
    }
    @RequestMapping(value = "/registrationInfo/getInfo")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getInfo(HttpServletRequest request){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        String registrationId=request.getParameter("registrationId");
        resultMap=registrationService.getInfo(registrationId);
        PatientInfo patientInfo=(PatientInfo)resultMap.get("patientInfo");
        DeptInfo deptInfo=(DeptInfo)resultMap.get("deptInfo");
        DoctorInfo doctorInfo=(DoctorInfo)resultMap.get("doctorInfo");
        Map<String,Object> readOnlyDate=new HashMap<String,Object>();
        readOnlyDate.put("name",patientInfo.getName());
        readOnlyDate.put("age",patientInfo.getAge());
        if(0==patientInfo.getSex()){
            readOnlyDate.put("sex","男");
        }else{
            readOnlyDate.put("sex","女");
        }
        readOnlyDate.put("id",patientInfo.getId());
        readOnlyDate.put("dept",deptInfo.getName());
        readOnlyDate.put("doctor",doctorInfo.getName());
        String medicalHistory=patientInfo.getMedicalHistory();
        String allergicHistory=patientInfo.getAllergicHistory();
        map.put("readOnlyDate",readOnlyDate);
        map.put("medicalHistory",medicalHistory);
        map.put("allergicHistory",allergicHistory);
        return  map;
    }

}
