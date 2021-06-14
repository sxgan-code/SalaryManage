package com.daniel.controller;

import com.daniel.domain.AttendanceData;
import com.daniel.services.AttendanceDataService;
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
public class AttendanceManage {
    @Autowired
    AttendanceDataService attendanceDataService;
    @RequestMapping("/openattendlist")
    public String openattendlist(){
        
        return "attendlist";
    }
    
    
    @RequestMapping("/attendancedata")
    public @ResponseBody
    Map<String, Object> attendancedata(Model model, Integer page, Integer limit, HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        List<AttendanceData> attendanceData = attendanceDataService.selectAttendList();
        PageInfo<AttendanceData> pageInfo = new PageInfo<>(attendanceData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/saveAttend")
    public @ResponseBody
    Map<String, Object> byNameReloadDeptData(Integer page, Integer limit,AttendanceData attend) {
        System.out.println("=============================" + page + "========" + limit+"====="+attend.toString());
        int insert = attendanceDataService.updateByPrimaryKey(attend);
        System.out.println("修改考勤信息"+insert);
        PageHelper.startPage(page, limit);
        List<AttendanceData> attendanceData = attendanceDataService.selectAttendList();
        PageInfo<AttendanceData> pageInfo = new PageInfo<>(attendanceData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addAttend")
    public   String addAttend(Model model, AttendanceData attendanceData, HttpServletRequest request) {
        System.out.println("-----------" +attendanceData.toString());
        AttendanceData oldattendanceData = attendanceDataService.selectByIdAndTime(attendanceData.getEmpid(), attendanceData.getAbsenteeismtime());
        
        if (oldattendanceData==null){
            attendanceDataService.insert(attendanceData);
            model.addAttribute("attendAddMsg",attendanceData.getEmpname()+"已成功添加"+attendanceData.getAbsenteeismtime()+"月的考勤信息！！");
        }else{
            System.out.println("===" +oldattendanceData.toString());
            model.addAttribute("attendAddMsg",oldattendanceData.getEmpname()+"已存在"+oldattendanceData.getAbsenteeismtime()+"月的考勤信息！！");
        }
        return "attendlist";
    }
    @RequestMapping("/deleteAttend")
    public String delAttend(Integer empid,String absenteeismtime, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            //将部门置为未分配
            int delattend = attendanceDataService.deleteByPrimaryKey(empid,absenteeismtime);
            System.out.println("============删除考勤信息================" + delattend);
            return "redirect:openattendlist";
        }
    }
    
    
}
