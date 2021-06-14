package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.mappers.TravelDataMapper;
import com.daniel.domain.TravelData;
import com.daniel.services.TravelDataService;

import java.util.List;

@Service
public class TravelDataServiceImpl implements TravelDataService{

    
    @Resource
    private TravelDataMapper travelDataMapper;

    @Override
    public int deleteByPrimaryKey(Integer empid,String traveltime) {
        return travelDataMapper.deleteByPrimaryKey(empid,traveltime);
    }
    
    @Override
    public int getTravelCount(String traveltime) {
        return travelDataMapper.getTravelCount(traveltime);
    }
    
    @Override
    public int insert(TravelData record) {
        return travelDataMapper.insert(record);
    }

    @Override
    public int insertSelective(TravelData record) {
        return travelDataMapper.insertSelective(record);
    }

    @Override
    public TravelData selectByPrimaryKey(Integer empid,String traveltime) {
        return travelDataMapper.selectByPrimaryKey(empid,traveltime);
    }

    
    public List<TravelData> selectTravelList(){
        return travelDataMapper.selectTravelList();
    }
    
    @Override
    public int updateByPrimaryKeySelective(TravelData record) {
        return travelDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TravelData record) {
        return travelDataMapper.updateByPrimaryKey(record);
    }

}
