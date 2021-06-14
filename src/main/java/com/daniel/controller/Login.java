package com.daniel.controller;

import com.daniel.domain.UserInfo;
import com.daniel.services.UserInfoService;
import com.mchange.v2.encounter.StrongEqualityEncounterCounter;
import com.mysql.cj.Session;
import com.sun.org.apache.xml.internal.serialize.ElementState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {
    @Autowired
    private UserInfoService userInfoService;
    
    @RequestMapping("/welcome")
    public String welcome() {
/*        for( int i = 0 ;i <100 ; i++ ){
            UserInfo userInfo = new UserInfo();
            userInfo.setName("test"+i);
            userInfo.setPassword("test"+i);
            userInfo.setAge(22);
            userInfo.setSex(i%2==0?"女":"男");
            userInfo.setNickname("nicknameTest"+i);
            userInfo.setUsertype(i%10==0?1:0);
            int insert = userInfoService.insert(userInfo);
            System.out.println("第"+i+"条数据"+(insert==1?"插入成功":"插入失败"));
        }*/
        return "login";
    }
    
    /**
     * 登录校验
     *
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model, String username, String password, HttpServletRequest request) {
        UserInfo userInfo;
        HttpSession session = request.getSession();
        String loginusername = (String) session.getAttribute("loginusername");
//        判断session域中是否有值
        if (loginusername == null) {
            if (username == null) {
                return "login";
            }
            userInfo = userInfoService.selectByName(username);
            if (userInfo != null) {
                if (userInfo.getPassword().equals(password)) {
                    System.out.println("成功登录！！!");
                    session.setAttribute("loginusername", username);
                    return "main";
                } else {
                    model.addAttribute("loginmsg", "用户密码错误！！！");
                    return "login";
                }
            } else {
                model.addAttribute("loginmsg", "该用户名不存在，请注册！！！");
                return "login";
            }
        } else {
            return "main";
        }
        
        
    }
    
    @RequestMapping("/openregister")
    public String openregister() {
        return "register";
    }
    
    /**
     * 注册
     *
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model, String username, String password, Integer age, String sex, String nickname) {
        if (username == null) {
            return "register";
        }
        UserInfo oldUserName = userInfoService.selectByName(username);
        
        if (oldUserName == null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(username);
            userInfo.setPassword(password);
            userInfo.setAge(age);
            userInfo.setSex(sex);
            userInfo.setNickname(nickname);
            userInfo.setUsertype(0);
            System.out.println(userInfo.toString());
            int insert = userInfoService.insert(userInfo);
            System.out.println("");
            if (userInfo != null && userInfo.getPassword().equals(userInfo.getPassword())) {
                System.out.println("成功注册！！!");
            }
            return "login";
        } else {
            model.addAttribute("msg", "用户名已存在");
            return "register";
        }
        
    }
}
