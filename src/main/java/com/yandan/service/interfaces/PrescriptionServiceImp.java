package com.yandan.service.interfaces;

import com.yandan.model.PrescriptionHis;
import com.yandan.model.PrescriptionInfo;

import java.util.HashMap;
import java.util.List;

public interface PrescriptionServiceImp {
    /**
     * 参数说明：将处方ID，挂号ID，状态整合到一个map中，根据这些参数的具体值获取处方信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部处方信息
     * @param paramMap
     * @return
     */
    List<PrescriptionInfo> getPrescriptionInfos(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将处方ID，挂号ID，状态整合到一个map中，根据这些参数的具体值获取处方信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部处方信息
     * @param paramMap
     * @return
     */
    List<PrescriptionHis> getPagePrescriptionHis(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将处方信息对象和其他参数整合到一个map中，在方法中进行拆分
     * 方法要求：要同时往对处方进行记录保存，
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int insertPrescription(HashMap<String,Object> paramMap);

}
