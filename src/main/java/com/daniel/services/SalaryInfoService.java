package com.daniel.services;

import com.daniel.domain.SalaryInfo;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryInfoService {
    
    List<SalaryInfo> selectAllSalaryList();
    
    BigDecimal getSalarySum(String distributiontime);
    
    List<SalaryInfo> selectSalaryList(String distributiontime);
    
    int insert(SalaryInfo record);
    
    int insertSelective(SalaryInfo record);
    
    int batchInsert(List<SalaryInfo> list);
    
    int deleteByPrimaryKey(Integer empid, String distributiontime);
    
    SalaryInfo selectByPrimaryKey(Integer empid, String distributiontime);
    
    int updateByPrimaryKeySelective(SalaryInfo record);
    
    int updateByPrimaryKey(SalaryInfo record);
}

