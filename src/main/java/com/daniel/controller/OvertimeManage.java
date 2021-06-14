package com.daniel.controller;

import com.daniel.domain.AttendanceData;
import com.daniel.domain.OvertimeData;
import com.daniel.services.AttendanceDataService;
import com.daniel.services.OvertimeDataService;
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

/**
 * 考勤管理
 */
@Controller
public class OvertimeManage {
    @Autowired
    OvertimeDataService overtimeDataService;
    @RequestMapping("/openovertimelist")
    public String openovertimelist(){
        
        return "overtimelist";
    }
    
    
    @RequestMapping("/overtimedata")
    public @ResponseBody
    Map<String, Object> overtimedata(Model model, Integer page, Integer limit, HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        List<OvertimeData> overtimeData = overtimeDataService.selectOvertimeList();
        PageInfo<OvertimeData> pageInfo = new PageInfo<>(overtimeData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/saveOvertime")
    public @ResponseBody
    Map<String, Object> byNameReloadOvertimeData(Integer page, Integer limit,OvertimeData overtime) {
        System.out.println("=============================" + page + "========" + limit+"====="+overtime.toString());
        int insert = overtimeDataService.updateByPrimaryKey(overtime);
        System.out.println("修改加班信息"+insert);
        PageHelper.startPage(page, limit);
        List<OvertimeData> overtimeData = overtimeDataService.selectOvertimeList();
        PageInfo<OvertimeData> pageInfo = new PageInfo<>(overtimeData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addOvertime")
    public String addOvertime(Model model, OvertimeData overtimeData, HttpServletRequest request) {
        System.out.println("-----------" +overtimeData.toString());
        OvertimeData oldOvertimeData = overtimeDataService.selectByPrimaryKey(overtimeData.getEmpid(), overtimeData.getOvertimemonth());
    
        if (oldOvertimeData==null){
            overtimeDataService.insert(overtimeData);
            model.addAttribute("overtimeAddMsg",overtimeData.getEmpname()+"已成功添加"+overtimeData.getOvertimemonth()+"月的加班信息！！");
    
        }else{
            System.out.println("===" +oldOvertimeData.toString());
            model.addAttribute("overtimeAddMsg",oldOvertimeData.getEmpname()+"已存在"+oldOvertimeData.getOvertimemonth()+"月的加班信息！！");
        }
        return "overtimelist";
    }
    @RequestMapping("/deleteOvertime")
    public String deleteOvertime(Integer empid,String overtimemonth, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            //将部门置为未分配
            int delovertime = overtimeDataService.deleteByPrimaryKey(empid,overtimemonth);
            System.out.println("============删除加班信息================" + delovertime);
            return "redirect:openovertimelist";
        }
    }
    
    
}
