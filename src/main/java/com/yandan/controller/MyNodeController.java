package com.yandan.controller;

import com.yandan.model.MyNode;
import com.yandan.service.MyNodeService;
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
public class MyNodeController {
    @Autowired
    private MyNodeService myNodeService;

    @RequestMapping("/myNode/insert")
    @ResponseBody
    public Map<String,Object> insertMyNode(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        paramMap.put("id",Integer.parseInt(request.getParameter("id")));
        paramMap.put("text",request.getParameter("text"));
        paramMap.put("pidStr",request.getParameter("pid"));
        paramMap.put("href",request.getParameter("href"));
        int flag=0;
        flag=myNodeService.insertMyNode(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败!");
        }
        return map;
    }
    @RequestMapping("/myNode/update")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateMyNode(HttpServletRequest request){
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        paramMap.put("id",Integer.parseInt(request.getParameter("id")));
        paramMap.put("text",request.getParameter("text"));
        paramMap.put("pidStr",request.getParameter("pid"));
        paramMap.put("href",request.getParameter("href"));
        int flag=0;
        flag=myNodeService.updateMyNode(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }
    @RequestMapping("/myNode/delete")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> deleteMyNodes(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String idsStr=request.getParameter("ids");
        List<String> ids=Arrays.asList(idsStr.split(","));
        int num1=ids.size();
        int num2=0;
        num2=myNodeService.deleteMyNodes(ids);
        map.put("msg",num1+"条记录，成功删除"+num2+"条记录");
        return map;
    }
    @RequestMapping("/myNode/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageMyNodes(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("size",Integer.parseInt(request.getParameter("rows")));
        paramMap.put("start",Integer.parseInt(request.getParameter("page")));
        List<MyNode> myNodes=myNodeService.getPageMyNodes(paramMap);
        int counts=myNodeService.getMyNodeCounts(new HashMap<String,Object>());
        map.put("rows",myNodes);
        map.put("total",counts);
        return map;
    }
    @RequestMapping("/myNode/getUserNodes")
    @ResponseBody
    public Map<String,Object> getUserNodes(HttpServletRequest request){
        String roleId=request.getParameter("roleId");
        HashMap<String,Object> map=new HashMap<String, Object>();
        List<MyNode> myNodes=myNodeService.getUserNodes(roleId);
        int counts=myNodeService.getUserNodeCounts(roleId);
        map.put("rows",myNodes);
        map.put("total",counts);
        return map;
    }
}
