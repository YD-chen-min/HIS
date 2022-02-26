package com.yandan.dao;

import com.yandan.model.PatientInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface PatientInfoDao {
    /**
     * 参数说明：将病人的姓名，身份证号，卡号整合到一个map中，根据这些参数的具体值获取病人信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部病人信息
     * @param paramMap
     * @return
     */
    List<PatientInfo> getPatientInfos(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将病人的姓名，身份证号，卡号整合到一个map中，根据这些参数的一页获取病人信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部病人信息
     * @param paramMap
     * @return
     */
    List<PatientInfo> getPagePatientInfos(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将需要插入病人对象和其他参数整合到map中传入方法，在方法中在进行拆分。
     * 插入成功返回1  失败返回0
     * 注意：一次只允许插入一名病人
     * @param paramMap
     * @return
     */
    int insertPatientInfo(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     * 参数说明：将要进行修改的信息整合到map中传入方法，在方法进行拆分
     *  插入成功返回1  失败返回0
     *  注意：一次只允许修改一名病人
     * @param paramMap
     * @return
     */
    int updatePatientInfo(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     * 传回符合条件的记录数
     * @param paramMap
     * @return
     */
    int getPatientCounts(@Param("paramMap") HashMap<String,Object> paramMap);
}
