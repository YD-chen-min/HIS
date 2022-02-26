package com.yandan.dao;

import com.yandan.model.RegistrationHis;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface RegistrationHisDao {
    List<RegistrationHis> getRegistrationHis(@Param("paramMap")HashMap<String,Object> paramMap);
    int insertRegistrationHis(@Param("paramMap")HashMap<String,Object> paramMap);
    int getRegistrationHisCounts(@Param("paramMap")HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需的信息整合到一个map中，在方法中进行拆分(结算时更新金额字段)
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int updateRegistrationHis(@Param("paramMap")HashMap<String ,Object> paramMap);
}
