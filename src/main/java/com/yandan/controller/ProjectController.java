package com.yandan.controller;

import com.yandan.model.Project;
import com.yandan.model.ProjectHis;
import com.yandan.model.ProjectInfo;
import com.yandan.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/project/getPage")
    @ResponseBody
    public Map<String,Object> getPageProjects(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("start",Integer.parseInt(request.getParameter("page")));
        paramMap.put("size",Integer.parseInt(request.getParameter("rows")));
        List<Project> projects=projectService.getPageProjects(paramMap);
        int counts=projectService.getProjectCounts(paramMap);
        map.put("rows",projects);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/project/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertProject(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("cost",Float.parseFloat(request.getParameter("cost")));
        int flag=0;
        flag=projectService.insertProject(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败，编号已存在");
        }
        return map;
    }
    @RequestMapping("/project/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateProject(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("deptId",request.getParameter("deptId"));
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("cost",Float.parseFloat(request.getParameter("cost")));
        int flag=0;
        flag=projectService.updateProject(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败，请检查修改信息");
        }
        return map;
    }
    @RequestMapping("/project/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteProjects(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String, Object>();
        List<String> ids= Arrays.asList(request.getParameter("ids").split(","));
        int num1=ids.size();
        int num2=0;
        num2=projectService.deleteProjects(ids);
        map.put("msg",num1+"条项目信息，成功删除"+num2+"条信息");
        return map;
    }
    @RequestMapping("/projectHis/getPage")
    @ResponseBody
    public Map<String,Object> getPageProjectHis(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("patientId",request.getParameter("patientId"));
        paramMap.put("start",Integer.parseInt(request.getParameter("page")));
        paramMap.put("size",Integer.parseInt(request.getParameter("rows")));
        List<ProjectHis> projectHis=projectService.getPageProjectHis(paramMap);
        int counts=projectService.getProjectHisCounts(paramMap);
        map.put("rows",projectHis);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/projectInfo/insert")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> insertProjectInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("diagnosticId",request.getParameter("p2"));
        paramMap.put("registrationId",request.getParameter("p3"));
        int count=Integer.parseInt(request.getParameter("p1"));
        int flag=0;
        for (int i=0;i<count;i++){
            paramMap.put("projectId",request.getParameter("id"+i));
            paramMap.put("initialCost",Float.parseFloat(request.getParameter("cost"+i)));
            flag=projectService.insertProjectInfo(paramMap);
        }
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败,请检查信息");
        }
        return map;
    }
    @RequestMapping("/projectInfo/settle")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateProjectInfo(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("finalCost",Float.parseFloat(request.getParameter("p1")));
        paramMap.put("id",request.getParameter("p2"));
        String roundingAmountStr=request.getParameter("roundingAmount");
        float roundingAmount;
        if(("undefined".equals(roundingAmountStr)||"".equals(roundingAmountStr))){
            roundingAmount=0;
        }else{
            roundingAmount=Float.parseFloat(roundingAmountStr);
        }
        paramMap.put("roundingAmount",roundingAmount);
        paramMap.put("status",2);
        int flag=0;
        flag=projectService.updateProjectInfo(paramMap);
        flag=projectService.updateProjectHis(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败，请检查修改信息");
        }
        return map;
    }
    @RequestMapping("/projectInfo/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteProjectInfos(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String, Object>();
        List<String> ids= Arrays.asList(request.getParameter("ids").split(","));
        int num1=ids.size();
        int num2=0;
        num2=projectService.deleteProjectInfos(ids);
        map.put("msg",num1+"条项目信息，成功删除"+num2+"条信息");
        return map;
    }
}
