package com.yandan.service;

import com.yandan.dao.DeptInfoDao;
import com.yandan.model.DeptInfo;
import com.yandan.service.interfaces.DeptServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class DeptService implements DeptServiceImp {
    @Autowired
    private DeptInfoDao deptInfoDao;

    /**
     * 此方法的paramMap里不能有内容
     * @param paramMap
     * @return
     */
    public List<DeptInfo> getAllDeptInfos(HashMap<String,Object> paramMap){
        return deptInfoDao.getDeptInfos(paramMap);
    }

    public List<DeptInfo> getPageDeptInfos(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return deptInfoDao.getPageDeptInfos(map);
    }
    public int getDeptInfosCounts(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }

        return deptInfoDao.getDeptInfoCounts(map);
    }
    @Override
    public List<DeptInfo> getDeptInfos(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        if(!("undefined".equals(name)||"".equals(name)||name==null)){
            map.put("name",name);
        }
        if(!("undefined".equals(id)||"".equals(id)||id==null)){
            map.put("id",id);
        }
        return deptInfoDao.getDeptInfos(map);
    }

    @Override
    public int insertDeptInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        String remark=(String)paramMap.get("remark");
        map.put("name",name);
        map.put("id",id);
        map.put("remark",remark);
        return deptInfoDao.insertDeptInfo(map);
    }

    @Override
    public int updateDeptInfo(HashMap<String, Object> paramMap) {
        HashMap<String,Object> map=new HashMap<String,Object>();
        String name=(String)paramMap.get("name");
        String id=(String)paramMap.get("id");
        String remark=(String)paramMap.get("remark");
        map.put("name",name);
        map.put("id",id);
        map.put("remark",remark);
        return deptInfoDao.updateDeptInfo(map);
    }

    @Override
    public int deleteDeptInfo(List<String> Ids) {
        return deptInfoDao.deleteDeptInfo(Ids);
    }
}
