package com.daniel.services;

import com.daniel.domain.OvertimeData;

import java.util.List;

public interface OvertimeDataService{
    
    List<OvertimeData> selectOvertimeList();
    
    int deleteByPrimaryKey(Integer empid,String overtimemonth);

    int insert(OvertimeData record);

    int insertSelective(OvertimeData record);

    OvertimeData selectByPrimaryKey(Integer empid,String overtimemonth);

    int updateByPrimaryKeySelective(OvertimeData record);

    int updateByPrimaryKey(OvertimeData record);

}
