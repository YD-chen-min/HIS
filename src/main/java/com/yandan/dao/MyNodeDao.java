package com.yandan.dao;

import com.yandan.model.MyNode;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MyNodeDao {
    List<MyNode> getPageMyNodes(@Param("paramMap")HashMap<String,Object> paramMap);
    int getMyNodeCounts(@Param("paramMap") HashMap<String,Object> paramMap);
    int updateMyNode(@Param("paramMap") HashMap<String,Object> paramMap);
    int deleteMyNodes(@Param("ids") List<String> ids);
    int insertMyNode(@Param("paramMap")HashMap<String,Object> paramMap);
    List<MyNode> getUserNodes(@Param("roleId") String roleId);
    int getUserNodeCounts(@Param("roleId")String roleId);
}
