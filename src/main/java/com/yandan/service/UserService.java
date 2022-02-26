package com.yandan.service;

import com.yandan.dao.UserDao;
import com.yandan.model.PageUser;
import com.yandan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public PageUser getPage(HttpServletRequest request) throws UnsupportedEncodingException {
        PageUser pageUser=new PageUser();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        String name=null;
        String id=null;
        int start=Integer.parseInt(request.getParameter("page"));
        int size=Integer.parseInt(request.getParameter("rows"));
        name=request.getParameter("name");
        id=request.getParameter("id");
        if(!("undefined".equals(name)||"".equals(name))){
            paramMap.put("name",name);
        }
        if(!("undefined".equals(id)||"".equals(id))){
            paramMap.put("id",id);
        }
        start=(start-1)*size;
        paramMap.put("start",start);
        paramMap.put("size",size);
        List<User> users=userDao.getUsers(paramMap);
        int count=userDao.getCount(paramMap);
        pageUser.setRows(users);
        pageUser.setTotal(count);
        return pageUser;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(HttpServletRequest request){
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String roleId=request.getParameter("roleId");
        return userDao.insert(id,name,password,roleId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int delete(String ids1){
        String[] ids=ids1.split(",");
        return userDao.delete(ids);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int userUpdate(HttpServletRequest request){
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        return userDao.userUpdate(id,name,password);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int adminUpdate(HttpServletRequest request){
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String roleId=request.getParameter("roleId");
        return userDao.adminUpdate(id,name,roleId);
    }
    public  User getStudent(String id){
        return  userDao.getUser(id);
    }
}
