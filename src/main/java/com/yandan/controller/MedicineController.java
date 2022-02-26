package com.yandan.controller;

import com.yandan.model.MedicineInfo;
import com.yandan.service.MedicineService;
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
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @RequestMapping("/medicine/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertMedicineInfo(HttpServletRequest request){
        HashMap<String,Object>map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("specification",request.getParameter("specification"));
        paramMap.put("inventory",request.getParameter("inventory"));
        paramMap.put("manufacturer",request.getParameter("manufacturer"));
        paramMap.put("remark",request.getParameter("remark"));
        paramMap.put("type",Integer.parseInt(request.getParameter("type")));
        paramMap.put("price",Float.parseFloat(request.getParameter("price")));
        paramMap.put("madeDate", request.getParameter("p1"));
        paramMap.put("buyDate",request.getParameter("p2"));
        int flag=0;
        flag=medicineService.insertMedicineInfo(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败!");
        }
        return map;
    }
    @RequestMapping("/medicine/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateMedicineInfo(HttpServletRequest request){
        HashMap<String,Object>map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("specification",request.getParameter("specification"));
        paramMap.put("inventory",request.getParameter("inventory"));
        paramMap.put("manufacturer",request.getParameter("manufacturer"));
        paramMap.put("remark",request.getParameter("remark"));
        paramMap.put("type",Integer.parseInt(request.getParameter("type")));
        paramMap.put("price",Float.parseFloat(request.getParameter("price")));
        int flag=0;
        flag=medicineService.updateMedicineInfo(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }
    @RequestMapping("/medicine/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageMedicineInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("type",request.getParameter("type"));
        int size=Integer.parseInt(request.getParameter("rows"));
        int start=Integer.parseInt(request.getParameter("page"));
        paramMap.put("size",size);
        paramMap.put("start",start);
        List<MedicineInfo> medicineInfos=medicineService.getPageMedicineInfos(paramMap);
        int counts=medicineService.getMedicineInfoCounts(paramMap);
        map.put("rows",medicineInfos);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/medicine/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteMedicineInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String idsString=request.getParameter("ids");
        List<String> ids= Arrays.asList( idsString.split(","));
        int num1=ids.size();
        System.out.println("num1="+num1);
        int num2=0;
        num2=medicineService.deleteMedicineInfo(ids);
        map.put("msg",num1+"条记录中成功删除了"+num2+"条记录");
        return map;
    }
    @RequestMapping("/medicine/get/{id}")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public List<MedicineInfo> getMedicineInfo(@PathVariable("id") String id){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("id",id);
        return medicineService.getMedicineInfo(paramMap);
    }
}
