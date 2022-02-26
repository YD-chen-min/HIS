package com.yandan.dao;

import com.yandan.model.Project;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ProjectDao {
    /**
     * 参数说明：将项目ID，科室ID，项目名称整合到一个map中，根据这些参数的具体值获取项目信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部项目信息
     * @param paramMap
     * @return
     */
    List<Project> getPageProjects(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将项目对象和其他参数整合到一个map中，在方法中进行拆分
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertProject(@Param("paramMap")HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需的信息整合到一个map中，在方法中进行拆分(根据id进行查找)
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int updateProject(@Param("paramMap")HashMap<String,Object> paramMap);
    /**
     * 参数说明，将选中要删除的部门的id整合到list中
     * 返回部门的个数
     * @param ids
     * @return
     */
    int deleteProjects(@Param("ids")List<String> ids);
    /**
     * 参数说明：将项目信息ID，挂号ID整合到一个map中，根据这些参数的具体值获取项目信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部项目信息
     * @param paramMap
     * @return
     */
    int getProjectCounts(@Param("paramMap")HashMap<String,Object> paramMap);
}
