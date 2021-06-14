package com.daniel.controller;

import com.daniel.domain.*;
import com.daniel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class PersonalManage {
    @Autowired
    private EmpInfoService empInfoService;
    @Autowired
    private SalaryInfoService salaryInfoService;
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
    @RequestMapping("/openPersonalPage")
    public String openPersonalPage(Model model ,Integer empid,String distributiontime){
        System.out.println("==========个人信息========编号："+empid+"======时间："+distributiontime);
        EmpInfo empInfo = empInfoService.selectBykeyFindDept(empid);
        model.addAttribute("empinfo",empInfo);
        System.out.println("=====empinfo" +empInfo.toString());
        SalaryInfo salaryInfo = salaryInfoService.selectByPrimaryKey(empid, distributiontime);
        model.addAttribute("salaryinfo",salaryInfo);
        AttendanceData attendanceData = attendanceDataService.selectByPrimaryKey(empid, distributiontime);
        model.addAttribute("incomeTaxCalculation",incomeTaxCalculation(salaryInfo));
        
        if (attendanceData==null){
            attendanceData = new AttendanceData();
            model.addAttribute("attendanceData",attendanceData);
            model.addAttribute("attendMsg","此员工本月没有缺勤记录");
        }else{
            model.addAttribute("attendanceData",attendanceData);
        }
    
        SanctionData sanctionData = sanctionDataService.selectByPrimaryKey(empid, distributiontime);
        if (sanctionData==null){
            sanctionData = new SanctionData();
            model.addAttribute("sanctionData",sanctionData);
            model.addAttribute("sanctionMsg","此员工本月没有奖罚记录");
        }else{
            model.addAttribute("sanctionData",sanctionData);
        }
        OvertimeData overtimeData = overtimeDataService.selectByPrimaryKey(empid, distributiontime);
        if (overtimeData==null){
            overtimeData = new OvertimeData();
            model.addAttribute("overtimeData",overtimeData);
            model.addAttribute("overtimeMsg","此员工本月没有加班记录记录");
        }else{
            model.addAttribute("overtimeData",overtimeData);
        }
        TravelData travelData = travelDataService.selectByPrimaryKey(empid, distributiontime);
        if (travelData==null){
            travelData = new TravelData();
            model.addAttribute("travelData",travelData);
            model.addAttribute("travelMsg","此员工本月没有加班记录记录");
        }else{
            model.addAttribute("travelData",travelData);
        }
        return "personal";
    }
    
    /**
     *
     * @return
     */
    public BigDecimal incomeTaxCalculation(SalaryInfo salaryInfo){
        SalaryInfo salaryInfo1 = new SalaryInfo();
        if (salaryInfo.getSum().compareTo(new BigDecimal(5000))==1&&(salaryInfo.getSum().compareTo(new BigDecimal(36000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(36000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.03"))));
        }else if ((salaryInfo.getSum().compareTo(new BigDecimal(36000))==1)&&(salaryInfo.getSum().compareTo(new BigDecimal(144000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(144000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.1")).subtract(new BigDecimal("2520.00"))));
        }else if (salaryInfo.getSum().compareTo(new BigDecimal(144000))==1&&(salaryInfo.getSum().compareTo(new BigDecimal(300000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(300000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.2")).subtract(new BigDecimal("16920.00"))));
        }else if (salaryInfo.getSum().compareTo(new BigDecimal(300000))==1&&(salaryInfo.getSum().compareTo(new BigDecimal(420000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(420000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.25")).subtract(new BigDecimal("31920.00"))));
        }else if (salaryInfo.getSum().compareTo(new BigDecimal(420000))==1&&(salaryInfo.getSum().compareTo(new BigDecimal(660000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(660000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.3")).subtract(new BigDecimal("52920.00"))));
        }else if (salaryInfo.getSum().compareTo(new BigDecimal(660000))==1&&(salaryInfo.getSum().compareTo(new BigDecimal(960000))==-1)||(salaryInfo.getSum().compareTo(new BigDecimal(960000))==0)){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.35")).subtract(new BigDecimal("85920.00"))));
        }else if(salaryInfo.getSum().compareTo(new BigDecimal(960000))==1){
            salaryInfo1.setSum(salaryInfo.getSum().subtract(salaryInfo.getSum().multiply(new BigDecimal("0.45")).subtract(new BigDecimal("181920.00"))));
        }else {
            salaryInfo1.setSum(salaryInfo.getSum());
        }
        return salaryInfo1.getSum().setScale(2);
        
    }
    

}
