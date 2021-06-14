package com.daniel.services;

import com.daniel.domain.TravelData;

import java.util.List;

public interface TravelDataService{
    
    List<TravelData> selectTravelList();
    
    int getTravelCount(String traveltime);
    
    int deleteByPrimaryKey(Integer empid,String traveltime);

    int insert(TravelData record);

    int insertSelective(TravelData record);

    TravelData selectByPrimaryKey(Integer empid,String traveltime);

    int updateByPrimaryKeySelective(TravelData record);

    int updateByPrimaryKey(TravelData record);

}
