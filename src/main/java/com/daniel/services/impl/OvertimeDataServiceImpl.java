package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.domain.OvertimeData;
import com.daniel.mappers.OvertimeDataMapper;
import com.daniel.services.OvertimeDataService;

import java.util.List;

@Service
public class OvertimeDataServiceImpl implements OvertimeDataService{
    
    @Resource
    private OvertimeDataMapper overtimeDataMapper;
    
    @Override
    public List<OvertimeData> selectOvertimeList(){
        return overtimeDataMapper.selectOvertimeList();
    }
    @Override
    public int deleteByPrimaryKey(Integer empid,String overtimemonth) {
        return overtimeDataMapper.deleteByPrimaryKey(empid,overtimemonth);
    }
    
    
    
    @Override
    public int insert(OvertimeData record) {
        return overtimeDataMapper.insert(record);
    }

    @Override
    public int insertSelective(OvertimeData record) {
        return overtimeDataMapper.insertSelective(record);
    }

    @Override
    public OvertimeData selectByPrimaryKey(Integer empid,String overtimemonth) {
        return overtimeDataMapper.selectByPrimaryKey(empid,overtimemonth);
    }

    @Override
    public int updateByPrimaryKeySelective(OvertimeData record) {
        return overtimeDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OvertimeData record) {
        return overtimeDataMapper.updateByPrimaryKey(record);
    }

}
