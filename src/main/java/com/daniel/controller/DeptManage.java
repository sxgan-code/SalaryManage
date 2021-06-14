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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.daniel.utils.CheckIsLogin.checkLogin;

@Controller
public class DeptManage {
    @Autowired
    private DeptInfoService deptInfoService;
    
    private static int countDept;
    private static int countEmp;
    
    @RequestMapping("/openDeptlist")
    public String openDeptlist(HttpServletRequest request){
        HttpSession session = request.getSession();

        countDept = deptInfoService.totalCountDept();//获取部门总条数，包含默认部门
        countEmp = deptInfoService.totalCountEmp();//获取员工总数
        session.setAttribute("countDept",countDept-1);
        session.setAttribute("countEmp",countEmp);
        return "deptlist";
    }
    @RequestMapping("/deptData")
    public @ResponseBody
    Map<String, Object> deptData(Model model,Integer page, Integer limit,HttpServletRequest request) {
        HttpSession session = request.getSession();
        countDept = deptInfoService.totalCountDept();
        countEmp = deptInfoService.totalCountEmp();
        session.setAttribute("countDept",countDept-1);
        session.setAttribute("countEmp",countEmp);
        
        System.out.println("=============================" + page + "========" + limit);
        PageHelper.startPage(page, limit);
        List<DeptInfo> deptInfos = deptInfoService.selectDeptList();
        PageInfo<DeptInfo> pageInfo = new PageInfo<DeptInfo>(deptInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    
    @RequestMapping("/byNameReloadDeptData")
    public @ResponseBody
    Map<String, Object> byNameReloadDeptData(Integer page, Integer limit,String deptname) {
        System.out.println("=============================" + page + "========" + limit+"====="+deptname);
        PageHelper.startPage(page, limit);
        List<DeptInfo> deptInfos = deptInfoService.selectDeptLikeNameList(deptname);
        PageInfo<DeptInfo> pageInfo = new PageInfo<DeptInfo>(deptInfos);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addDept")
    public String addemp(Model model, DeptInfo deptInfo, HttpServletRequest request) {
//        if (checkLogin(request)) {
//            return "login";
//        }
        DeptInfo deptByid = deptInfoService.selectByPrimaryKey(deptInfo.getDeptid());
        DeptInfo deptByName = deptInfoService.getDeptByName(deptInfo.getDeptname());
        if (deptByid == null) {
            if (deptByName == null){
                int insert = deptInfoService.insert(deptInfo);
                System.out.println("=========插入部门=============" + insert);
                return "redirect:openDeptlist";
            }else {
                model.addAttribute("addDeptMsg", deptByName.getDeptname()+"已存在！！！");
                return "deptlist";
            }
        } else {
            model.addAttribute("addDeptMsg", deptByid.getDeptid()+"部门编号已存在！！！");
            return "deptlist";
        }
    }
    @RequestMapping("/updateDept")
    public String updateDept(Model model, DeptInfo deptInfo, HttpServletRequest request) {
//        if (checkLogin(request)) {
//            return "login";
//        }
        DeptInfo deptByName = deptInfoService.getDeptByName(deptInfo.getDeptname());
        if (deptByName == null){
            int i = deptInfoService.updateByPrimaryKey(deptInfo);
            System.out.println("=========编辑部门=============" + i);
            return "redirect:openDeptlist";
        }else {
            model.addAttribute("updateDeptMsg", deptByName.getDeptname()+"已存在！！！");
            return "deptlist";
        }
    }
    @RequestMapping("/deleteDept")
    public String deleteEmp(Integer deptid, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            System.out.println(deptid);
            if (deptid != null) {
                //将部门置为未分配
                int dept = deptInfoService.updateEmpinfoDept(deptid);
                System.out.println("==========更改未分配条数："+dept);
                int i = deptInfoService.deleteByPrimaryKey(deptid);
                
                System.out.println("============删除部门================" + i);
            }
            return "redirect:openDeptlist";
        }
    }
}
