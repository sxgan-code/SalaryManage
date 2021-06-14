package com.daniel.services;

import com.daniel.domain.SanctionData;

import java.util.List;

public interface SanctionDataService{
    
    List<SanctionData> selectSanctionList();

    int deleteByPrimaryKey(Integer empid,String sanctiontime);

    int insert(SanctionData record);

    int insertSelective(SanctionData record);

    SanctionData selectByPrimaryKey(Integer empid,String sanctiontime);

    int updateByPrimaryKeySelective(SanctionData record);

    int updateByPrimaryKey(SanctionData record);

}
