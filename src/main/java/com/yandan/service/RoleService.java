package com.yandan.service;

import com.yandan.dao.RoleDao;
import com.yandan.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> getPageRoles(HashMap<String,Object> paramMap){
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        HashMap<String,Object> map=new HashMap<String,Object>();
        start=(start-1)*size;
        map.put("size",size);
        map.put("start",start);
        return roleDao.getPageRoles(map);
    }

    public int getRoleCounts(){
        return roleDao.getRoleCounts();
    }

    public int addRolePower(HashMap<String,Object> paramMap){
        List<String> nodeIds=(List<String>)paramMap.get("nodeIds");
        String roleId=(String)paramMap.get("roleId");
        HashMap<String,Object> map=new HashMap<String, Object>();
        int flag=0;
        map.put("roleId",roleId);
        for (String nodeId : nodeIds) {
            map.put("nodeId",nodeId);
            flag+= roleDao.addRolePower(map);
        }
        return flag;
    }

    public int removeRolePower(HashMap<String,Object> paramMap){
        return roleDao.removeRolePower(paramMap);
    }
    public int deleteRoles(List<String> ids){
        int flag=0;
        HashMap<String ,Object> paramMap=new HashMap<String, Object>();
        flag=roleDao.deleteRoles(ids);
        for (String id : ids) {
            paramMap.put("role_id",id);
            flag=roleDao.removeRolePower(paramMap);
        }
        return flag;
    }

    public int updateRole(HashMap<String,Object> paramMap){
        return roleDao.updateRole(paramMap);
    }

    public int insertRole(HashMap<String,Object> paramMap){
        return roleDao.insertRole(paramMap);
    }
}
