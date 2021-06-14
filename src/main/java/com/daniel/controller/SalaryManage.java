package com.daniel.controller;

import com.daniel.domain.*;
import com.daniel.services.*;
import com.daniel.utils.SystemTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.daniel.utils.CheckIsLogin.checkLogin;

@Controller
public class SalaryManage {
    @Autowired
    EmpInfoService empInfoService;
    
    @Autowired
    SalaryInfoService salaryInfoService;
    @Autowired
    private OvertimeDataService overtimeDataService;
    @Autowired
    private SanctionDataService sanctionDataService;
    @Autowired
    private AttendanceDataService attendanceDataService;
    @Autowired
    private TravelDataService travelDataService;
    @Autowired
    private DeptInfoService deptInfoService;
    @RequestMapping("/openAddSalaryPage")
    public String openAddSalaryPage(Integer empid, Model model){
        System.out.println("===========openAddsalaryPage====="+empid);
        if (empid!=null){
            EmpInfo empInfo = empInfoService.selectByPrimaryKey(empid);
            model.addAttribute("emptosalary",empInfo);
        }
        return "addsalary";
    }
    @RequestMapping("/clickOpenAddSalaryPage")
    public String openAddSalaryPage(Model model){
        EmpInfo empInfoMsg = new EmpInfo();
        empInfoMsg.setEmpname("请点击左侧列表录入薪资");
        model.addAttribute("emptosalary",empInfoMsg);
        return "addsalary";
    }
    @RequestMapping("/clickOpenSalaryListPage")
    public String clickOpenSalaryListPage(Model model){
        setSalaryListValue(model);
        return "salarylist";
    }
    
    private void setSalaryListValue(Model model) {
        String systemTimeString = SystemTime.getSystemTimeString();
        model.addAttribute("empCount",deptInfoService.totalCountEmp());
        model.addAttribute("attendanceCount",attendanceDataService.getAttendCount(systemTimeString));
        model.addAttribute("travelCount",travelDataService.getTravelCount(systemTimeString));
        model.addAttribute("salarySum",salaryInfoService.getSalarySum(systemTimeString));
        System.out.println("empCount:"+deptInfoService.totalCountEmp()+"attendanceCount"+attendanceDataService.getAttendCount(systemTimeString)+"travelCount"+travelDataService.getTravelCount(systemTimeString)+"salarySum"+salaryInfoService.getSalarySum(systemTimeString));
    }
    
    @RequestMapping("/addSalary")
    public String addSalary(Model model, SalaryInfo salaryInfo){
        SalaryInfo oldSalaryInfo = salaryInfoService.selectByPrimaryKey(salaryInfo.getEmpid(), salaryInfo.getDistributiontime());
        if (oldSalaryInfo == null) {
            System.out.println("====未计算===salaryInfo："+salaryInfo.toString());
            SalaryInfo calculateSalaryResult = calculateSalary(salaryInfo);
            System.out.println("====已计算===calculateSalaryResult："+calculateSalaryResult.toString());
            String systemTimeString = SystemTime.getSystemTimeString();
            System.out.println("==========系统时间========="+systemTimeString);
            salaryInfoService.insert(calculateSalaryResult);
            setSalaryListValue(model);
            return "salarylist";
        } else {
            model.addAttribute("addSalayMsg", "该员工已存在"+salaryInfo.getDistributiontime()+"月的工资单！！");
            EmpInfo empInfoMsg = new EmpInfo();
            empInfoMsg.setEmpname("请点击左侧列表录入薪资");
            model.addAttribute("emptosalary",empInfoMsg);
            return "addsalary";
        }
        
    }
    @RequestMapping("/salarydata")
    public @ResponseBody
    Map<String, Object> salarydata(Model model, Integer page, Integer limit, HttpServletRequest request) {
        String systemTimeString = SystemTime.getSystemTimeString();
        PageHelper.startPage(page, limit);
        List<SalaryInfo> salaryInfos = salaryInfoService.selectSalaryList(systemTimeString);
        PageInfo<SalaryInfo> pageInfo = new PageInfo<>(salaryInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    
    @RequestMapping("/byTimeReloadData")
    public @ResponseBody
    Map<String, Object> byTimeReloadData(Integer page, Integer limit,String time) {
        System.out.println("==========工资列表重载=================" + page + "========" + limit+"===="+time);
        PageHelper.startPage(page, limit);
        List<SalaryInfo> salaryInfos = salaryInfoService.selectSalaryList(time);
        PageInfo<SalaryInfo> pageInfo = new PageInfo<>(salaryInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/recount")
    public String recount(Model model){
        List<SalaryInfo> oldsalaryInfos = salaryInfoService.selectAllSalaryList();
        for (SalaryInfo salaryInfo : oldsalaryInfos) {
            SalaryInfo salaryInfo1 = calculateSalary(salaryInfo);
            salaryInfoService.updateByPrimaryKey(salaryInfo1);
        }
        setSalaryListValue(model);
        return "salarylist";
    }
    /**
     * 计算薪资
     * @param salaryInfo
     * @return
     */
    
    public SalaryInfo calculateSalary(SalaryInfo salaryInfo){
        //加班费计算
        OvertimeData overtimeData = overtimeDataService.selectByPrimaryKey(salaryInfo.getEmpid(), salaryInfo.getDistributiontime());
        if (overtimeData != null) {
            salaryInfo.setOvertimepay(overtimeData.getOvertimesalary());
        }
        
        //奖励
        SanctionData sanctionData = sanctionDataService.selectByPrimaryKey(salaryInfo.getEmpid(), salaryInfo.getDistributiontime());
        if (sanctionData != null) {
            salaryInfo.setBonus(sanctionData.getReward());
            salaryInfo.setFine(sanctionData.getFine());
        }else {
            salaryInfo.setFine(new BigDecimal(0));
        }
        
        //罚款
        //考勤罚款
        AttendanceData attendanceData = attendanceDataService.selectByPrimaryKey(salaryInfo.getEmpid(), salaryInfo.getDistributiontime());
        //加上奖惩制度罚款
        if (attendanceData != null) {
            salaryInfo.setFine(attendanceData.getFine().add(salaryInfo.getFine()));
        }
        //补贴
        TravelData travelData = travelDataService.selectByPrimaryKey(salaryInfo.getEmpid(), salaryInfo.getDistributiontime());
        if (travelData != null) {
            salaryInfo.setSubsidy(travelData.getTravelsalary());
        }
        //总计
        salaryInfo.setSum(salaryInfo.getBasesalary().add(salaryInfo.getBonus()).subtract(salaryInfo.getFine()).add(salaryInfo.getHousallowance()).add(salaryInfo.getOvertimepay()).add(salaryInfo.getSubsidy()).add(salaryInfo.getSuperannuation()));
        
        System.out.println(salaryInfo.toString());
        return salaryInfo;
    }
    @RequestMapping("/saveSalaryData")
    public @ResponseBody
    Map<String, Object> saveSalaryData(Integer page, Integer limit,SalaryInfo salaryInfo) {
        System.out.println("=============================" + page + "========" + limit+"====="+salaryInfo.toString());
        int insert = salaryInfoService.updateByPrimaryKey(salaryInfo);
        System.out.println("保存工资信息"+insert);
        PageHelper.startPage(page, limit);
        List<SalaryInfo> salaryInfos = salaryInfoService.selectSalaryList(salaryInfo.getDistributiontime());
        PageInfo<SalaryInfo> pageInfo = new PageInfo<>(salaryInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    
    @RequestMapping("/deleteSalary")
    public String delAttend(Integer empid,String distributiontime, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            //删除
            int del = salaryInfoService.deleteByPrimaryKey(empid,distributiontime);
            System.out.println("============删除考勤信息================" + del);
            return "redirect:clickOpenSalaryListPage";
        }
    }
}
