package com.yandan.dao;

import com.yandan.model.Project;
import com.yandan.model.ProjectHis;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ProjectHisDao {
    /**
    * 参数说明：将项目信息对象和其他参数整合到一个map中，在方法中进行拆分
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertProjectHis(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将项目信息ID，病人身份证号码，医生身份证号码整合到一个map中，根据这些参数的具体值获取项目信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部项目信息
     * @param paramMap
     * @return
     */
    List<ProjectHis> getPageProjectHis(@Param("paramMap") HashMap<String,Object> paramMap);

    int getProjectHisCounts(@Param("paramMap") HashMap<String,Object> paramMap);
    int updateProjectHis(@Param("paramMap")HashMap<String,Object> paramMap);
}
