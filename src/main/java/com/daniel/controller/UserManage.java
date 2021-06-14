package com.daniel.controller;

import com.daniel.domain.UserInfo;
import com.daniel.services.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.daniel.utils.CheckIsLogin.checkLogin;

@Controller
public class UserManage {
    @Autowired
    private UserInfoService userInfoService;
    
    @RequestMapping("/userlist")
    public String userList() {
        return "main";
    }
    
    @RequestMapping("/userdata")
    public @ResponseBody Map<String, Object> userData(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserInfo> userInfos = userInfoService.selectUserInfoList();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/byNameReloadData")
    public @ResponseBody
    Map<String, Object> byNameReloadData(Integer page, Integer limit,String name) {
        System.out.println("=============================" + page + "========" + limit+"===="+name);
        PageHelper.startPage(page, limit);
        List<UserInfo> userInfos = userInfoService.selectByLikeName(name);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/openAddPage")
    public String openAddPage() {
        return "addUser";
    }
    
    @RequestMapping("/addUser")
    public String addUser(Model model, UserInfo user, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        }
        UserInfo userInfo = userInfoService.selectByName(user.getName());
        if (userInfo == null) {
            System.out.println(user.toString());
            int insert = userInfoService.insert(user);
            
            System.out.println("======================" + insert);
            return "main";
        } else {
            model.addAttribute("addMsg", "此用户已经存在！！！");
            return "addUser";
        }
    }
    
    @RequestMapping("/openEditPage")
    public String openEditPage(Model model, Integer id, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        }
        System.out.println("成功=========================================================");
        System.out.println(id);
        UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        model.addAttribute("editUser", userInfo);
        return "editUser";
    }
    
    @RequestMapping("/editUser")
    public String editUser(Model model, UserInfo user, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        }
        System.out.println(user.toString());
        int i = userInfoService.updateByPrimaryKey(user);
        System.out.println("编辑======================" + i);
        return "main";
    }
    
    @RequestMapping("/deleteUser")
    public String deleteUser(Model model, Integer id, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            System.out.println(id);
            if (id != null) {
                int i = userInfoService.deleteByPrimaryKey(id);
                System.out.println("删除============================" + i);
            }
            return "main";
        }
    }
    
}
