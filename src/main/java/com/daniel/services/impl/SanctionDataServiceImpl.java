package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.mappers.SanctionDataMapper;
import com.daniel.domain.SanctionData;
import com.daniel.services.SanctionDataService;

import java.util.List;

@Service
public class SanctionDataServiceImpl implements SanctionDataService{

    @Resource
    private SanctionDataMapper sanctionDataMapper;
    
    @Override
    public List<SanctionData> selectSanctionList() {
        return sanctionDataMapper.selectSanctionList();
    }
    
    @Override
    public int deleteByPrimaryKey(Integer empid,String sanctiontime) {
        return sanctionDataMapper.deleteByPrimaryKey(empid,sanctiontime);
    }

    @Override
    public int insert(SanctionData record) {
        return sanctionDataMapper.insert(record);
    }

    @Override
    public int insertSelective(SanctionData record) {
        return sanctionDataMapper.insertSelective(record);
    }

    @Override
    public SanctionData selectByPrimaryKey(Integer empid,String sanctiontime) {
        return sanctionDataMapper.selectByPrimaryKey(empid,sanctiontime);
    }

    @Override
    public int updateByPrimaryKeySelective(SanctionData record) {
        return sanctionDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SanctionData record) {
        return sanctionDataMapper.updateByPrimaryKey(record);
    }

}
