package com.yandan.dao;

import com.yandan.model.DeptInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DeptInfoDao {
    /**
     * 参数说明：将部门的名称，部门编号整合到一个map中，根据这些参数的具体值获取部门信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部部门信息
     * @param paramMap
     * @return
     */
    List<DeptInfo> getDeptInfos(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将需要插入部门对象和其他参数整合到map中传入方法，在方法中在进行拆分。
     * 插入成功返回1  失败返回0
     * 注意：一次只允许插入一名病人
     * @param paramMap
     * @return
     */
    int insertDeptInfo(@Param("paramMap")HashMap<String,Object> paramMap);

    /**
     * 参数说明：将要进行修改的信息整合到map中传入方法，在方法进行拆分
     *  插入成功返回1  失败返回0
     *  注意：一次只允许修改一名部门
     * @param paramMap
     * @return
     */
    int updateDeptInfo(@Param("paramMap")HashMap<String,Object> paramMap);

    /**
     * 参数说明：传入部门的编号，根据部门编号进行删除
     * 返回删除信息的条数
     * @param Ids
     * @return
     */
    int deleteDeptInfo(@Param("Ids")List<String> Ids);

    /**
     * 获取一页信息
     * @param paramMap
     * @return
     */
    List<DeptInfo> getPageDeptInfos(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     * 获取符合条件的记录数
     * @param paramMap
     * @return
     */
    int getDeptInfoCounts(@Param("paramMap") HashMap<String,Object> paramMap);
}
