package com.yandan.dao;

import com.yandan.model.DiagnosticHis;
import com.yandan.model.PrescriptionHis;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface PrescriptionHisDao {
    /**
     * 参数说明：将处方，病人身份证号码，医生身份证号码整合到一个map中，根据这些参数的具体值获取诊断处方记录
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部处方信息记录
     * @param paramMap
     * @return
     */
    List<PrescriptionHis> getPrescriptionHis(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需要的整合到一个map中，在方法中进行拆分
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertPrescriptionHis(@Param("paramMap") HashMap<String,Object> paramMap);
    int getPrescriptionHisCounts(@Param("paramMap") HashMap<String,Object> paramMap);
    int updatePrescriptionHis(@Param("paramMap") HashMap<String,Object> paramMap);
}
