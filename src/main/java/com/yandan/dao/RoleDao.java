package com.yandan.dao;

import com.yandan.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<Map<String,Object>> getRoles();
    List<Role> getPageRoles(@Param("paramMap")HashMap<String,Object> paramMap);
    int getRoleCounts();
    int updateRole(@Param("paramMap")HashMap<String,Object> paramMap);
    int deleteRoles(@Param("ids")List<String> ids);
    int addRolePower(@Param("paramMap")HashMap<String,Object> paramMap);
    int removeRolePower(@Param("paramMap")HashMap<String,Object> paramMap);
    int insertRole(@Param("paramMap")HashMap<String,Object> paramMap);
}
