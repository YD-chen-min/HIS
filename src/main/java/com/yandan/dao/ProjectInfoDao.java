package com.yandan.dao;

import com.yandan.model.Project;
import com.yandan.model.ProjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ProjectInfoDao {
    /**
     * 参数说明：将项目信息ID，挂号ID整合到一个map中，根据这些参数的具体值获取项目信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部项目信息
     * @param paramMap
     * @return
     */
    List<ProjectInfo> getPageProjectInfos(@Param("paramMap")HashMap<String,Object> paramMap);
    /**
     * 参数说明：将项目信息参数整合到一个map中，在方法中进行拆分
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertProjectInfo(@Param("paramMap")HashMap<String,Object> paramMap);
    /**
     * 参数说明，将选中要删除的项目信息的id整合到list中
     * 方法说明 删除只能在信息表中进行删除，不能删除记录表中的记录
     * 返回删除信息的条数
     * @param ids
     * @return
     */
    int deleteProjectInfos(@Param("ids") List<String> ids);
    int getProjectInfoCounts(@Param("paramMap")HashMap<String,Object> paramMap);
    int updateProjectInfo(@Param("paramMap")HashMap<String,Object> paramMap);
}
