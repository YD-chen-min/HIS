package com.yandan.dao;

import com.yandan.model.DiagnosticHis;
import com.yandan.model.DiagnosticInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DiagnosticInfoDao {
    /**
     * 参数说明：将诊断信息对象和其他参数整合到一个map中，在方法中进行拆分
     * 方法要求：要同时往对诊断进行记录保存，
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertDiagnosticInfo(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     * 参数说明：将诊断ID，挂号ID，状态整合到一个map中，根据这些参数的具体值获取诊断信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部诊断信息
     * @param paramMap
     * @return
     */
    List<DiagnosticInfo> getDiagnosticInfos(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将诊断ID，病人身份证号码，医生身份证号码整合到一个map中，根据这些参数的具体值获取诊断信息记录
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部诊断信息记录
     * @param paramMap
     * @return
     */
    /**
     * 参数说明，将选中要删除的诊断信息的id整合到list中
     * 方法说明 删除只能在信息表中进行删除，不能删除记录表中的诊断记录
     * 返回删除信息的条数
     * @param ids
     * @return
     */
    int deleteDiagnosticInfos(@Param("ids") List<String> ids);

    /**
     * 获取符合条件的一页信息
     * @param paramMap
     * @return
     */
    List<DiagnosticInfo> getPageDiagnosticInfos(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     *获取符合条件的记录个数
     * @param paramMap
     * @return
     */
    int getDiagnosticInfoCounts(@Param("paramMap") HashMap<String,Object> paramMap);
}
