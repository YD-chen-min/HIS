package com.yandan.controller;

import com.yandan.dao.NodeDao;
import com.yandan.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

   @Autowired
   private NodeDao nodeDao;

    @RequestMapping("/tree/node/{pid}")
    @ResponseBody
    public List<Node> getNodes(@PathVariable("pid") int pid){
            return nodeDao.getNodes(pid);
    }

    @RequestMapping("/tree/node/getUserNode")
    @ResponseBody
    public List<Node> getUserNodes(@RequestParam("roleId") String roleId){
        return nodeDao.getUserNodes(roleId);
    }

    @RequestMapping("/tree/node/all")
    @ResponseBody
public List<Node> getAllNodes(){
        return nodeDao.getAll();
}


}
