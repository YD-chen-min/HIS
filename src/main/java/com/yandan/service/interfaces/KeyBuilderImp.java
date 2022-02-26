package com.yandan.service.interfaces;


import java.util.HashMap;

public interface KeyBuilderImp {
    /***
     *1.根据传入的参数包括需要key的类型，以及对应key需要的参数
     * 2.key的类型为1，诊断信息id  需要的参数 医生的工号 16位(6+8+2)
     * key的类型为2 ,3 处方信息id(2)或者项目检查id (3) 需要的参数 诊断信息的id 17位(16+1)
     * key的类型为4 挂号信息id  无参  14位(6+8)
     *
     */
    String getKey(HashMap<String,Object> paramMap);

}
