package com.yandan.service.interfaces;

import com.yandan.model.DoctorInfo;

import java.util.HashMap;
import java.util.List;

public interface DoctorServiceImp {
    /**
     * 参数说明：将医生的姓名，身份证号，工号，科室编号整合到一个map中，根据这些参数的具体值获取医生信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部医生信息
     * @param paramMap
     * @return
     */
    List<DoctorInfo> getDoctorInfos(HashMap<String,Object> paramMap);
    /**
     * 参数说明：将需要插入医生对象和其他参数整合到map中传入方法，在方法中在进行拆分。
     * 插入成功返回1  失败返回0
     * 注意：一次只允许插入一名病人
     * @param paramMap
     * @return
     */
    int insertDoctorInfo(HashMap<String,Object> paramMap);

    /**
     * 参数说明：将要进行修改的信息整合到map中传入方法，在方法进行拆分
     *  插入成功返回1  失败返回0
     *  注意：一次只允许修改一名医生
     * @param paramMap
     * @return
     */
    int updateDoctorInfo(HashMap<String,Object> paramMap);

    /**
     * 参数说明：传入医生的工号，根据工号进行删除
     * 返回删除的记录条数
     * @param workIds
     * @return
     */
    int deleteDoctorInfo(List<String> workIds);
}
