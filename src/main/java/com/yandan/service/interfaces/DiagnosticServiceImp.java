package com.yandan.service.interfaces;

import com.yandan.model.DiagnosticHis;
import com.yandan.model.DiagnosticInfo;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DiagnosticServiceImp {
    /**
     * 参数说明：将诊断信息对象和其他参数整合到一个map中，在方法中进行拆分
     * 方法要求：要同时往对诊断进行记录保存，
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    Map<String, Object> insertDiagnostic(HashMap<String,Object> paramMap);

    /**
     * 参数说明：将诊断ID，挂号ID，状态整合到一个map中，根据这些参数的具体值获取诊断信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部诊断信息
     * @param paramMap
     * @return
     */
    List<DiagnosticInfo> getDiagnosticInfos(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将诊断ID，病人身份证号码，医生身份证号码整合到一个map中，根据这些参数的具体值获取诊断信息记录
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部诊断信息记录
     * @param paramMap
     * @return
     */
    List<DiagnosticHis> getDiagnosticHis(HashMap<String,Object> paramMap);

    /**
     * 参数说明，将选中要删除的诊断信息的id整合到list中
     * 方法说明 删除只能在信息表中进行删除，不能删除记录表中的诊断记录
     * 返回删除信息的条数
     * @param ids
     * @return
     */
    int deleteDiagnosticInfos(List<String> ids);


}
