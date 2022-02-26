package com.yandan.service;

import com.yandan.dao.MyNodeDao;
import com.yandan.dao.NodeDao;
import com.yandan.model.MyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MyNodeService {
    @Autowired
    private MyNodeDao myNodeDao;
    public List<MyNode> getPageMyNodes(HashMap<String,Object> paramMap){
        HashMap<String,Object> map=new HashMap<String,Object>();
        int size=(int)paramMap.get("size");
        int start=(int)paramMap.get("start");
        start=(start-1)*size;
        map.put("start",start);
        map.put("size",size);
        return myNodeDao.getPageMyNodes(map);
    }

    public int getMyNodeCounts(HashMap<String,Object> paramMap){
        return myNodeDao.getMyNodeCounts(paramMap);
    }

    public int updateMyNode(HashMap<String,Object> paramMap){
        int id=(int)paramMap.get("id");
        String text=(String)paramMap.get("text");
        String pidStr=(String) paramMap.get("pidStr");
        String href=(String) paramMap.get("href");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(!("undefined".equals(text)||"".equals(text)||text==null)){
            map.put("text",text);
        }
        if(!("undefined".equals(pidStr)||"".equals(pidStr)||pidStr==null)){
            map.put("pid",Integer.parseInt(pidStr));
        }else{
            map.put("pid",0);
        }
        if(!("undefined".equals(href)||"".equals(href)||href==null)){
            map.put("href",href);
        }else{
            map.put("href","");
        }
        map.put("id",id);
        return myNodeDao.updateMyNode(map);
    }
     public int deleteMyNodes(List<String> ids){
        return myNodeDao.deleteMyNodes(ids);
     }
     public int insertMyNode(HashMap<String,Object> paramMap){
         int id=(int)paramMap.get("id");
         String text=(String)paramMap.get("text");
         String pidStr=(String) paramMap.get("pidStr");
         String href=(String) paramMap.get("href");
         HashMap<String,Object> map=new HashMap<String,Object>();
         if(!("undefined".equals(text)||"".equals(text)||text==null)){
             map.put("text",text);
         }
         if(!("undefined".equals(pidStr)||"".equals(pidStr)||pidStr==null)){
             map.put("pid",Integer.parseInt(pidStr));
         }else{
             map.put("pid",0);
         }
         if(!("undefined".equals(href)||"".equals(href)||href==null)){
             map.put("href",href);
         }else{
             map.put("href","");
         }
         map.put("id",id);
         return myNodeDao.insertMyNode(map);
     }
     public List<MyNode> getUserNodes(String roleId){
        return myNodeDao.getUserNodes(roleId);
     }
     public int getUserNodeCounts(String roleId){
        return myNodeDao.getUserNodeCounts(roleId);
     }
}
