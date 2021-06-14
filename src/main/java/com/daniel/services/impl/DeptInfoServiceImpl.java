package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.domain.DeptInfo;
import com.daniel.mappers.DeptInfoMapper;
import com.daniel.services.DeptInfoService;

import java.util.List;

@Service
public class DeptInfoServiceImpl implements DeptInfoService{

    @Resource
    private DeptInfoMapper deptInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer deptid) {
        return deptInfoMapper.deleteByPrimaryKey(deptid);
    }
    
    @Override
    public List<DeptInfo> selectDeptList() {
        return deptInfoMapper.selectDeptList();
    }
    @Override
    public List<DeptInfo> selectDeptLikeNameList(String deptname) {
        return deptInfoMapper.selectDeptLikeNameList(deptname);
    }
    
    public int totalCountDept(){
        return deptInfoMapper.totalCountDept();
    }
    public int totalCountEmp(){
        return deptInfoMapper.totalCountEmp();
    }
    public int updateEmpinfoDept(Integer deptid){
        return deptInfoMapper.updateEmpinfoDept(deptid);
    }
    
    @Override
    public int insert(DeptInfo record) {
        return deptInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(DeptInfo record) {
        return deptInfoMapper.insertSelective(record);
    }

    @Override
    public DeptInfo selectByPrimaryKey(Integer deptid) {
        return deptInfoMapper.selectByPrimaryKey(deptid);
    }
    
    public DeptInfo getDeptByName(String deptname){
        return deptInfoMapper.getDeptByName(deptname);
    }
    
    @Override
    public int updateByPrimaryKeySelective(DeptInfo record) {
        return deptInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DeptInfo record) {
        return deptInfoMapper.updateByPrimaryKey(record);
    }

}
