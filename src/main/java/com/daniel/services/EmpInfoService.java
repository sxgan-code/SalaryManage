package com.daniel.services;

import com.daniel.domain.EmpInfo;

import java.util.List;

public interface EmpInfoService{
    
    EmpInfo selectBykeyFindDept(Integer empid);
    
    int deleteByPrimaryKey(Integer empid);

    int insert(EmpInfo record);

    int insertSelective(EmpInfo record);
    
    List<EmpInfo> selectEmpInfoList();
    
    EmpInfo selectByPrimaryKey(Integer empid);
    
    EmpInfo selectEmpDeptByPrimaryKey(Integer empid);
    
    List<EmpInfo> selectByLikeName(String empname);
    
    int updateByPrimaryKeySelective(EmpInfo record);

    int updateByPrimaryKey(EmpInfo record);
    
    EmpInfo getEmpCertid(String empid);
}
