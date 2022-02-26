package com.yandan.controller;

import com.yandan.model.DiagnosticHis;
import com.yandan.model.DoctorInfo;
import com.yandan.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DiagnosticController {
    @Autowired
    private DiagnosticService diagnosticService;

    @RequestMapping("/diagnostic/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertDiagnosticInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String, Object>();
        paramMap.put("doctorWorkId",request.getParameter("p1"));
        paramMap.put("registrationId",request.getParameter("p2"));
        paramMap.put("selfReported",request.getParameter("selfReported"));
        paramMap.put("accidentDate",request.getParameter("p3"));
        paramMap.put("conclusion",request.getParameter("conclusion"));
        paramMap.put("remark",request.getParameter("remark"));
        map=diagnosticService.insertDiagnostic(paramMap);
        int flag=(int)map.get("flag");
        if(flag>0){
            map.put("msg","添加成功!请开处方");
        }else{
            map.put("msg","添加失败!");
        }
        map.put("registrationId",request.getParameter("p2"));
        return map;
    }
    @RequestMapping("/diagnostic/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageDiagnosticHis(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("patientId",request.getParameter("patientId"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("doctorId",request.getParameter("doctorId"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<DiagnosticHis> diagnosticHis=diagnosticService.getPageDiagnosticHis(paramMap);
        int counts=diagnosticService.getPageDiagnosticHisCounts(paramMap);
        map.put("rows",diagnosticHis);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/diagnostic/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteDeptInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String idsString=request.getParameter("ids");
        List<String> ids= Arrays.asList( idsString.split(","));
        int num1=ids.size();
        int num2=0;
        num2=diagnosticService.deleteDiagnosticInfos(ids);
        map.put("msg",num1+"条记录中成功删除了"+num2+"条记录");
        return map;
    }
}
