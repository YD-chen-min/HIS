package com.yandan.controller;

import com.yandan.model.PrescriptionHis;
import com.yandan.model.RegistrationHis;
import com.yandan.service.PrescriptionService;
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
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping("/prescription/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertPrescription(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object>map=new HashMap<String, Object>();
        paramMap.put("diagnosticId",request.getParameter("p2"));
        paramMap.put("invalidReason",request.getParameter("invalidReason"));
        int counts=Integer.parseInt(request.getParameter("p1"));
        float initialCost=0;
        int flag=0;
        for (int i=0;i<counts;i++){
            paramMap.put("medicineIds",request.getParameter("id"+i));
            paramMap.put("medicineNames",request.getParameter("name"+i));
            float price=Float.parseFloat(request.getParameter("price"+i));
            float amount=Float.parseFloat(request.getParameter("amount"+i));
            initialCost=price*amount;
            paramMap.put("initialCost",initialCost);
            paramMap.put("remark",request.getParameter("remark"+i));
            flag+=prescriptionService.insertPrescription(paramMap);
        }
        map.put("msg",counts+"条处方，成功添加"+flag/2+"处方信息");
        return map;
    }
    @RequestMapping("/prescriptionHis/getPage")
    @ResponseBody
    public Map<String,Object> getPagePrescriptionHis(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("patientId",request.getParameter("patientId"));
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<PrescriptionHis> prescriptionHis=prescriptionService.getPagePrescriptionHis(paramMap);
        int counts=prescriptionService.getPrescriptionHisCount(paramMap);
        map.put("rows",prescriptionHis);
        map.put("total",counts);
        return  map;
    }
    @RequestMapping(value = "/prescription/settle")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updatePrescriptionInfo(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        String id=request.getParameter("p2");
        String roundingAmountStr=request.getParameter("roundingAmount");
        float roundingAmount;
        if(("undefined".equals(roundingAmountStr)||"".equals(roundingAmountStr))){
            roundingAmount=0;
        }else{
            roundingAmount=Float.parseFloat(roundingAmountStr);
        }
        float finalCost=Float.parseFloat(request.getParameter("p1"));
        paramMap.put("id",id);
        paramMap.put("roundingAmount",roundingAmount);
        paramMap.put("finalCost",finalCost);
        paramMap.put("status",2);
        int flag=0;
        flag=prescriptionService.updatePrescriptionInfo(paramMap);
        flag=prescriptionService.updatePrescriptionHis(paramMap);
        if(flag>0){
            map.put("msg","结算成功!");
        }else{
            map.put("msg","结算失败!，请联系管理员进行数据检查");
        }
        return  map;
    }
}
