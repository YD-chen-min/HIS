package com.yandan.controller;

import com.yandan.dao.UserDao;
import com.yandan.model.DoctorInfo;
import com.yandan.model.PageUser;
import com.yandan.model.User;
import com.yandan.service.DoctorService;
import com.yandan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") String id){
        return userService.getStudent(id);
    }

    @RequestMapping("user/insert")
    @ResponseBody
    public Map<String,String> insert(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        int result=0;
        result= userService.insert(request);
        if(result>0){
            map.put("msg","添加成功!");
        }else{
            map.put("msg","账号已存在,添加失败!");
        }
        return  map;
    }

    @RequestMapping("/user/UserUpdate")
    @ResponseBody
    public Map<String,String> userUpdate(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        int result=0 ;
        result=userService.userUpdate(request);
        if(result>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }

    @RequestMapping("/user/AdminUpdate")
    @ResponseBody
    public Map<String,String> adminUpdate(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        int result=0 ;
        result=userService.adminUpdate(request);
        if(result>0){
            map.put("msg","修改成功!");
        }else{
            map.put("msg","修改失败!");
        }
        return map;
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public Map<String,String>  delete(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        String ids=request.getParameter("ids");
        int result=0;
        result=userService.delete(ids);
        if(result>0){
            map.put("msg","删除成功!");
        }else{
            map.put("msg","删除失败!");
        }
        return  map;
    }

    @RequestMapping("/user/get")
    @ResponseBody
    public PageUser getUsers(HttpServletRequest request) throws UnsupportedEncodingException {

        return userService.getPage(request);
    }

    @PostMapping("/user/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id=request.getParameter("id");
        String password=request.getParameter("password");
        User user=userService.getStudent(id);
        String workId=user.getWorkId();
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("workId",workId);
        List<DoctorInfo> doctorInfos=doctorService.getDoctorInfos(paramMap);
        if(user==null){
           return "/error.jsp";
        }
        if(password.equals(user.getPassword())){
            request.getSession().setAttribute("user",user);
            if(doctorInfos.size()>0){
                request.getSession().setAttribute("doctor",doctorInfos.get(0));
            }
            return "redirect:/UI.jsp";
        }else{
            return "/error.jsp";
        }

    }
}
