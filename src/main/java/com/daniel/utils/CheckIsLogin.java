package com.daniel.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckIsLogin {
    /**
     * 检查是否登录
     * @return
     */
    public static boolean checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String loginusername = (String)session.getAttribute("loginusername");
        if (loginusername == null) {
            return false;
        }else {
            return false;
        }
        
    }
}
