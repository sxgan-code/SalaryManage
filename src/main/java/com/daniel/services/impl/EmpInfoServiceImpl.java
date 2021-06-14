package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.domain.EmpInfo;
import com.daniel.mappers.EmpInfoMapper;
import com.daniel.services.EmpInfoService;

import java.util.List;

@Service
public class EmpInfoServiceImpl implements EmpInfoService{

    @Resource
    private EmpInfoMapper empInfoMapper;
    
    @Override
    public EmpInfo selectBykeyFindDept(Integer empid) {
        return empInfoMapper.selectBykeyFindDept(empid);
    }
    
    @Override
    public int deleteByPrimaryKey(Integer empid) {
        return empInfoMapper.deleteByPrimaryKey(empid);
    }

    @Override
    public int insert(EmpInfo record) {
        return empInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(EmpInfo record) {
        return empInfoMapper.insertSelective(record);
    }
    
    @Override
    public EmpInfo getEmpCertid(String empid) {
        return empInfoMapper.getEmpCertid(empid);
    }
    
    public List<EmpInfo> selectEmpInfoList(){
        return empInfoMapper.selectEmpInfoList();
    }
    
    @Override
    public EmpInfo selectByPrimaryKey(Integer empid) {
        return empInfoMapper.selectByPrimaryKey(empid);
    }
    
    public EmpInfo selectEmpDeptByPrimaryKey(Integer empid) {
        return empInfoMapper.selectEmpDeptByPrimaryKey(empid);
    }
    
    public List<EmpInfo> selectByLikeName(String empname){
        return empInfoMapper.selectByLikeName(empname);
    }
    
    @Override
    public int updateByPrimaryKeySelective(EmpInfo record) {
        return empInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EmpInfo record) {
        return empInfoMapper.updateByPrimaryKey(record);
    }

}
