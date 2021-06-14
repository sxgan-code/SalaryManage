package com.daniel.controller;

import com.daniel.domain.DeptInfo;
import com.daniel.domain.EmpInfo;
import com.daniel.domain.UserInfo;
import com.daniel.services.DeptInfoService;
import com.daniel.services.EmpInfoService;
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
public class EmployeeManage {
    @Autowired
    private EmpInfoService empInfoService;
    @Autowired
    private DeptInfoService deptInfoService;
    
    
    
    @RequestMapping("/emplist")
    public String empList() {
        /*for( int i = 0 ;i <500 ; i++ ){
            EmpInfo empInfo = new EmpInfo();
            empInfo.setEmpname("empTest"+(1000+i));
            empInfo.setEmpsex(i%3==0?"男":"女");
            empInfo.setEmpage((int)(Math.random()*30+20));
            empInfo.setEmpsex(i%3==0?"男":"女");
            empInfo.setCertid("42265219920325"+(int)(Math.random()*9000+999));
            empInfo.setEntrydate(new Date());
            empInfo.setDeptid(i%3==0?1001:i%7==0?1008:1005);
            int insert = empInfoService.insert(empInfo);
            System.out.println("第"+i+"条数据"+(insert==1?"插入成功":"插入失败")+"===="+empInfo.toString());
        }*/
        return "emplist";
    }
    
    @RequestMapping("/empdata")
    public @ResponseBody
    Map<String, Object> empData(Integer page, Integer limit) {
//        System.out.println("=============================" + page + "========" + limit);
        PageHelper.startPage(page, limit);
        List<EmpInfo> empInfos = empInfoService.selectEmpInfoList();
//        System.out.println(empInfos.toString());
        PageInfo<EmpInfo> pageInfo = new PageInfo<EmpInfo>(empInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    @RequestMapping("/byEmpNameReloadData")
    public @ResponseBody
    Map<String, Object> byEmpNameReloadData(Integer page, Integer limit,String empname) {
        System.out.println("=============================" + page + "========" + limit+"===="+empname);
        PageHelper.startPage(page, limit);
        List<EmpInfo> empInfos = empInfoService.selectByLikeName(empname);
        PageInfo<EmpInfo> pageInfo = new PageInfo<EmpInfo>(empInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    
    @RequestMapping("/openAddEmpPage")
    public String openAddEmpPage(Model model) {
        List<DeptInfo> deptInfos = deptInfoService.selectDeptList();
        model.addAttribute("deptinfoList", deptInfos);
        return "addEmp";
    }
    
    @RequestMapping("/addEmp")
    public String addemp(Model model, EmpInfo emp, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        }
        EmpInfo empCertid = empInfoService.getEmpCertid(emp.getCertid());
        if (empCertid == null) {
            int insert = empInfoService.insert(emp);
            System.out.println("=========插入员工=============" + insert);
            return "emplist";
        } else {
            model.addAttribute("addEmpMsg", "此员工已经存在！！！");
            return "addEmp";
        }
    }
    
    @RequestMapping("/openEditEmpPage")
    public String openEditPage(Model model, Integer empid, HttpServletRequest request) {
//        if (checkLogin(request)) {
//            return "login";
//        }
        EmpInfo empInfo = empInfoService.selectEmpDeptByPrimaryKey(empid);
        List<DeptInfo> deptInfos = deptInfoService.selectDeptList();
        model.addAttribute("deptinfolist",deptInfos);
        model.addAttribute("editemp", empInfo);
        return "editEmp";
    }
    
    @RequestMapping("/editEmp")
    public String editEmp(Model model, EmpInfo empInfo, HttpServletRequest request) {
//        if (checkLogin(request)) {
//            return "login";
//        }
        System.out.println(empInfo.toString());
        int i = empInfoService.updateByPrimaryKey(empInfo);
        System.out.println("编辑员工======================" + i);
        return "emplist";
    }
    @RequestMapping("/deleteEmp")
    public String deleteEmp(Integer empid, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            System.out.println(empid);
            if (empid != null) {
                int i = empInfoService.deleteByPrimaryKey(empid);
                System.out.println("============删除员工================" + i);
            }
            return "emplist";
        }
    }
}
