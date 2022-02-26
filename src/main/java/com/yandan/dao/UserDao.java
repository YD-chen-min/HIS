package com.yandan.dao;

import com.yandan.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getUsers(@Param("paramMap") Map<String,Object> paramMap);
    int delete(@Param("ids") String []ids);
    int getCount(@Param("paramMap") Map<String,Object> paramMap);
    int userUpdate(@Param("id") String id,@Param("name") String name,@Param("password") String password);
    int adminUpdate(@Param("id") String id,@Param("name") String name,@Param("roleId") String roleId);
    User getUser(@Param("id") String id);
    int insert(@Param("id")String id,@Param("name")String name,@Param("password")String password,@Param("roleId")String roleId);

}
