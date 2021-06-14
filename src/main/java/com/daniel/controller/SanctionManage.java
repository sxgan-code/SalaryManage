package com.daniel.controller;

import com.daniel.domain.SanctionData;
import com.daniel.domain.SanctionData;
import com.daniel.services.SanctionDataService;
import com.daniel.services.SanctionDataService;
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
import java.util.Scanner;

import static com.daniel.utils.CheckIsLogin.checkLogin;

/**
 * 考勤管理
 */
@Controller
public class SanctionManage {
    @Autowired
    SanctionDataService sanctionDataService;
    @RequestMapping("/opensanctionlist")
    public String opensanctionlist(){
        
        return "sanctionlist";
    }
    
    
    @RequestMapping("/sanctiondata")
    public @ResponseBody
    Map<String, Object> sanctiondata(Model model, Integer page, Integer limit, HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        List<SanctionData> sanctionData = sanctionDataService.selectSanctionList();
        PageInfo<SanctionData> pageInfo = new PageInfo<>(sanctionData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/saveSanction")
    public @ResponseBody
    Map<String, Object> byNameReloadSanctionData(Integer page, Integer limit, SanctionData sanction) {
        System.out.println("=============================" + page + "========" + limit+"====="+sanction.toString());
        int insert = sanctionDataService.updateByPrimaryKey(sanction);
        System.out.println("修改奖罚信息"+insert);
        PageHelper.startPage(page, limit);
        List<SanctionData> SanctionData = sanctionDataService.selectSanctionList();
        PageInfo<SanctionData> pageInfo = new PageInfo<>(SanctionData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addSanction")
    public String addSanction(Model model, SanctionData sanctionData, HttpServletRequest request) {
        System.out.println("============"+sanctionData.toString());
        SanctionData oldSanctionData = sanctionDataService.selectByPrimaryKey(sanctionData.getEmpid(),sanctionData.getSanctiontime());
        if (oldSanctionData==null){
            int insert = sanctionDataService.insert(sanctionData);
            model.addAttribute("sanctionAddMsg",sanctionData.getEmpname()+"已成功添加"+sanctionData.getSanctiontime()+"月的奖罚信息！！");
            System.out.println("插入奖罚信息"+insert);
        }else{
            System.out.println("===" +oldSanctionData.toString());
            model.addAttribute("sanctionAddMsg",oldSanctionData.getEmpname()+"已存在"+oldSanctionData.getSanctiontime()+"月的奖罚信息！！");
        }
        return "sanctionlist";
    }
    @RequestMapping("/deleteSanction")
    public String deleteSanction(Integer empid,String sanctiontime, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            //将部门置为未分配
            int delSancton = sanctionDataService.deleteByPrimaryKey(empid,sanctiontime);
            System.out.println("============删除奖罚信息================" + delSancton);
            return "redirect:opensanctionlist";
        }
    }
    
    
}
