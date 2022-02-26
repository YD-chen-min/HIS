package com.yandan.dao;

import com.yandan.model.MedicineInfo;
import com.yandan.model.PrescriptionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MedicineInfoDao {
    /**
            * 参数说明：将药品的名称，药品编号，药品分类整合到一个map中，根据这些参数的具体值获取药品信息
     * 注意：参数个数不变，根据参数具体的值（是否为空啥的）生成相应的sql语句，默认为全部药品信息
     * @param paramMap
     * @return
             */
    List<MedicineInfo> getMedicineInfos(@Param("paramMap") HashMap<String,Object> paramMap);
    /**
     * 参数说明：将需要插入药品对象和其他参数整合到map中传入方法，在方法中在进行拆分。
     * 插入成功返回1  失败返回0
     * 注意：一次只允许插入一条药品信息
     * @param paramMap
     * @return
     */
    int insertMedicineInfo(@Param("paramMap")HashMap<String,Object> paramMap);

    /**
     * 参数说明：将要进行修改的信息整合到map中传入方法，在方法进行拆分(根据id进行查询)
     *  插入成功返回1  失败返回0
     *  注意：一次只允许修改一条药品信息
     * @param paramMap
     * @return
     */
    int updateMedicineInfo(@Param("paramMap")HashMap<String,Object> paramMap);

    /**
     * 参数说明：传入药品编号，根据药品编号进行删除
     * 返回删除的条数
     * @param ids
     * @return
     */
    int deleteMedicineInfo(@Param("ids")List<String> ids);

    /**
     * 获得一页符合条件的数据
     * @param paramMap
     * @return
     */
    List<MedicineInfo> getPageMedicineInfos(@Param("paramMap") HashMap<String,Object> paramMap);

    /**
     * 获取符合条件的数据个数
     * @param paramMap
     * @return
     */
    int getMedicineInfoCounts(@Param("paramMap") HashMap<String,Object> paramMap);
}
