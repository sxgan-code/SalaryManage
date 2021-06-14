package com.daniel.mappers;

import com.daniel.domain.DeptInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptInfoMapper {
    /**
     * delete by primary key
     * @param deptid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer deptid);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(DeptInfo record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(DeptInfo record);

    /**
     * select by primary key
     * @param deptid primary key
     * @return object by primary key
     */
    DeptInfo selectByPrimaryKey(Integer deptid);
    
    DeptInfo getDeptByName(String deptname);
    
    int totalCountDept();
    
    int totalCountEmp();
    
    int updateEmpinfoDept(Integer deptid);
    
    List<DeptInfo> selectDeptList();
    
    List<DeptInfo> selectDeptLikeNameList(String deptname);
    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(DeptInfo record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(DeptInfo record);
}