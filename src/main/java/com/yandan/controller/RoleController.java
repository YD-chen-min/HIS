package com.yandan.controller;

import com.yandan.dao.RoleDao;
import com.yandan.model.Role;
import com.yandan.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDao roleDao;

    @RequestMapping("/role/get")
    @ResponseBody
    public List<Map<String,Object>> getRoles(){
        return roleDao.getRoles();
    }

    @RequestMapping("/role/getPage")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> getPageRoles(HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<String, Object>();
        HashMap<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("start",Integer.parseInt(request.getParameter("page")));
        paramMap.put("size",Integer.parseInt(request.getParameter("rows")));
        List<Role> roles=roleService.getPageRoles(paramMap);
        int counts=roleService.getRoleCounts();
        map.put("total",counts);
        map.put("rows",roles);
        return map;
    }
    @RequestMapping("/role/delete/{ids}")
    @ResponseBody
    @Transactional(propagation =Propagation.REQUIRED)
    public Map<String,Object> deleteRoles(@PathVariable("ids")String ids){
        List<String> idList= Arrays.asList(ids.split(","));
        HashMap<String,Object> map=new HashMap<String, Object>();
        int flag=0;
        flag=roleService.deleteRoles(idList);
        if(flag>0){
            map.put("msg","删除成功!");
        }else{
            map.put("msg","删除失败!");
        }
        return map;
    }
    @RequestMapping("/role/update")
    @ResponseBody
    public Map<String,Object> updateRole(HttpServletRequest request){
        HashMap<String ,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("name",request.getParameter("name"));
        paramMap.put("id",request.getParameter("id"));
        int flag=0;
        flag=roleService.updateRole(paramMap);
        if(flag>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }
    @RequestMapping("/role/addRolePower")
    @ResponseBody
    public Map<String,Object> addRolePower(HttpServletRequest request){
        HashMap<String ,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        List<String> nodeIds=Arrays.asList(request.getParameter("nodeIds").split(","));
        int num1=nodeIds.size();
        int num2=0;
        paramMap.put("nodeIds",nodeIds);
        paramMap.put("roleId",request.getParameter("roleId"));
       num2= roleService.addRolePower(paramMap);
       map.put("msg",num1+"项权限中，成功授权了"+num2+"项权限");
       return map;
    }
    @RequestMapping("/role/removeRolePower")
    @ResponseBody
    public Map<String,Object> removeRolePower(HttpServletRequest request){
        HashMap<String ,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        int flag=0;
        paramMap.put("role_id",request.getParameter("roleId"));
        paramMap.put("node_id",request.getParameter("nodeId"));
        flag=roleService.removeRolePower(paramMap);
        if(flag>0){
            map.put("msg","权限移除成功!");
        }else{
            map.put("msg","权限移除失败!");
        }
        return map;
    }

    @RequestMapping("/role/insert")
    @ResponseBody
    public Map<String,Object> insertRole(HttpServletRequest request){
        HashMap<String ,Object> paramMap=new HashMap<String, Object>();
        HashMap<String,Object> map=new HashMap<String, Object>();
        paramMap.put("id",request.getParameter("id"));
        paramMap.put("name",request.getParameter("name"));
        int flag=0;
        flag=roleService.insertRole(paramMap);
        if(flag>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","添加失败!");
        }
        return map;
    }
}
