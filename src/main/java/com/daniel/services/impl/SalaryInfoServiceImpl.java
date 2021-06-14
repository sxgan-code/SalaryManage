package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import com.daniel.domain.SalaryInfo;
import com.daniel.mappers.SalaryInfoMapper;
import com.daniel.services.SalaryInfoService;

@Service
public class SalaryInfoServiceImpl implements SalaryInfoService {
    
    @Resource
    private SalaryInfoMapper salaryInfoMapper;
    
    
    @Override
    public List<SalaryInfo> selectAllSalaryList() {
        return salaryInfoMapper.selectAllSalaryList();
    }
    
    @Override
    public BigDecimal getSalarySum(String distributiontime) {
        return salaryInfoMapper.getSalarySum(distributiontime);
    }
    
    @Override
    public List<SalaryInfo> selectSalaryList(String distributiontime) {
        return salaryInfoMapper.selectSalaryList(distributiontime);
    }
    
    @Override
    public int insert(SalaryInfo record) {
        return salaryInfoMapper.insert(record);
    }
    
    @Override
    public int insertSelective(SalaryInfo record) {
        return salaryInfoMapper.insertSelective(record);
    }
    
    @Override
    public int batchInsert(List<SalaryInfo> list) {
        return salaryInfoMapper.batchInsert(list);
    }
    
    @Override
    public int deleteByPrimaryKey(Integer empid, String distributiontime) {
        return salaryInfoMapper.deleteByPrimaryKey(empid, distributiontime);
    }
    
    @Override
    public SalaryInfo selectByPrimaryKey(Integer empid, String distributiontime) {
        return salaryInfoMapper.selectByPrimaryKey(empid, distributiontime);
    }
    
    @Override
    public int updateByPrimaryKeySelective(SalaryInfo record) {
        return salaryInfoMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(SalaryInfo record) {
        return salaryInfoMapper.updateByPrimaryKey(record);
    }
}

