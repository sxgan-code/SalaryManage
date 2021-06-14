package com.daniel.controller;

import com.daniel.domain.OvertimeData;
import com.daniel.domain.TravelData;
import com.daniel.services.OvertimeDataService;
import com.daniel.services.TravelDataService;
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
public class TravelManage {
    @Autowired
    TravelDataService travelDataService;
    @RequestMapping("/opentravellist")
    public String opentravellist(){
        
        return "travellist";
    }
    
    
    @RequestMapping("/traveldata")
    public @ResponseBody
    Map<String, Object> traveldata(Model model, Integer page, Integer limit, HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        List<TravelData> travelData = travelDataService.selectTravelList();
        PageInfo<TravelData> pageInfo = new PageInfo<>(travelData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/saveTravel")
    public @ResponseBody
    Map<String, Object> byNameReloadTravelData(Integer page, Integer limit,TravelData travel) {
        System.out.println("=============================" + page + "========" + limit+"====="+travel.toString());
        int insert = travelDataService.updateByPrimaryKey(travel);
        System.out.println("修改出差信息"+insert);
        PageHelper.startPage(page, limit);
        List<TravelData> travelData = travelDataService.selectTravelList();
        PageInfo<TravelData> pageInfo = new PageInfo<>(travelData);
        Map<String, Object> map = new HashMap<>();
//        数据封装
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addTravel")
    public String addTravel(Model model, TravelData travelData, HttpServletRequest request) {
        System.out.println("-----------" +travelData.toString());
        TravelData oldtravelData = travelDataService.selectByPrimaryKey(travelData.getEmpid(), travelData.getTraveltime());
    
        if (oldtravelData==null){
            travelDataService.insert(travelData);
            model.addAttribute("travelAddMsg",travelData.getEmpname()+"已成功添加"+travelData.getTraveltime()+"月的出差信息！！");
        }else{
            System.out.println("===" +oldtravelData.toString());
            model.addAttribute("travelAddMsg",oldtravelData.getEmpname()+"已存在"+oldtravelData.getTraveltime()+"月的出差信息！！");
        }
        return "travellist";
    }
    @RequestMapping("/deleteTravel")
    public String deleteTravel(Integer empid,String traveltime, HttpServletRequest request) {
        if (checkLogin(request)) {
            return "login";
        } else {
            //将部门置为未分配
            int delovertime = travelDataService.deleteByPrimaryKey(empid,traveltime);
            System.out.println("============删除出差信息================" + delovertime);
            return "redirect:opentravellist";
        }
    }
    
    
}
