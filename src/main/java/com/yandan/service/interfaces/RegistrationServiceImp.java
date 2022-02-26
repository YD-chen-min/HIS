package com.yandan.service.interfaces;

import com.yandan.model.RegistrationHis;
import com.yandan.model.RegistrationInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RegistrationServiceImp {
    /**
     * 参数说明：将所需的的信息整合到map中，再在方法中进行拆分
     * 方法要求每次进行插入的同时要记录保存
     * @param paramMap
     * @return
     */
    Map<String,Object> registerInfo(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需的的信息整合到map中，再在方法中进行拆分
     * @param paramMap
     * @return
     */
    List<RegistrationInfo> getRegistrationInfos(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需的的信息整合到map中，再在方法中进行拆分
     * @param paramMap
     * @return
     */
    List<RegistrationHis> getRegistrationHis(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将所需的信息整合到一个map中，在方法中进行拆分
     * 成功返回1  失败返回0
     * @param paramMap
     * @return
     */
    int updateRegistrationInfo(HashMap<String ,Object> paramMap);
    /**
     * 参数说明，将选中要删除的挂号的id整合到list中
     * 方法说明 删除只能在信息表中进行删除，不能删除记录表中的记录
     * 返回删除信息的条数
     * @param ids
     * @return
     */
    int deleteRegistrationInfo(List<String> ids);
}
