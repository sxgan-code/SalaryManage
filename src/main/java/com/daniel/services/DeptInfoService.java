package com.daniel.services;

import com.daniel.domain.DeptInfo;

import java.util.List;

public interface DeptInfoService{


    int deleteByPrimaryKey(Integer deptid);

    int insert(DeptInfo record);

    int insertSelective(DeptInfo record);
    
    List<DeptInfo> selectDeptList();
    
    List<DeptInfo> selectDeptLikeNameList(String deptname);
    
    int totalCountDept();
    
    int totalCountEmp();
    
    int updateEmpinfoDept(Integer deptid);
    
    DeptInfo getDeptByName(String deptname);
    
    DeptInfo selectByPrimaryKey(Integer deptid);

    int updateByPrimaryKeySelective(DeptInfo record);

    int updateByPrimaryKey(DeptInfo record);

}
