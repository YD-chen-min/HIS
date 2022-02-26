package com.yandan.controller;

import com.yandan.model.DeptInfo;
import com.yandan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/dept/getAll")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public List<DeptInfo> getAllDeptInfos(){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        return deptService.getAllDeptInfos(paramMap);
    }
    @RequestMapping("/dept/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageDeptInfos(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        paramMap.put("id",id);
        paramMap.put("name",name);
        paramMap.put("start",start);
        paramMap.put("size",size);
        List<DeptInfo> deptInfos=deptService.getPageDeptInfos(paramMap);
        int counts=deptService.getDeptInfosCounts(paramMap);
        map.put("rows",deptInfos);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/dept/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertDeptInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String remark=request.getParameter("remark");
        paramMap.put("id",id);
        paramMap.put("name",name);
        paramMap.put("remark",remark);
        int flag=0;
        flag=deptService.insertDeptInfo(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败，科室编号已存在");
        }
        return map;
    }
    @RequestMapping("/dept/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateDeptInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String remark=request.getParameter("remark");
        paramMap.put("id",id);
        paramMap.put("name",name);
        paramMap.put("remark",remark);
        int flag=0;
        flag=deptService.updateDeptInfo(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败，科室编号已存在");
        }
        return map;
    }
    @RequestMapping("/dept/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteDeptInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String idsString=request.getParameter("ids");
        List<String> ids= Arrays.asList( idsString.split(","));
        int num1=ids.size();
        int num2=0;
        num2=deptService.deleteDeptInfo(ids);
        map.put("msg",num1+"条记录中成功删除了"+num2+"条记录");
        return map;
    }
    @RequestMapping("/dept/get")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getDeptInfos(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        paramMap.put("id",id);
        paramMap.put("name",name);
        List<DeptInfo> deptInfos=deptService.getDeptInfos(paramMap);
        map.put("deptInfos",deptInfos);
        return map;
    }
    @RequestMapping("/dept/get/{id}")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getDeptInfo(@PathVariable("id") String id){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        paramMap.put("id",id);
        List<DeptInfo> deptInfos=deptService.getDeptInfos(paramMap);
        map.put("deptInfos",deptInfos);
        return map;
    }
}
