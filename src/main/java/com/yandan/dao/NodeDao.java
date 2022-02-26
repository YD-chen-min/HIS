package com.yandan.dao;

import com.yandan.model.MyNode;
import com.yandan.model.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NodeDao {
    List<Node> getNodes(@Param("pid") int pid);
    List<Node> getAll();
    List<Node> getUserNodes(@Param("roleId") String roleId);
}
