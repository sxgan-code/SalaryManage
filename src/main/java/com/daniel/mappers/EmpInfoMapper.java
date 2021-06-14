package com.daniel.mappers;

import com.daniel.domain.EmpInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpInfoMapper {
    @Select("select e.empid, e.empname, e.empsex, e.empage, e.certid, e.entrydate, e.deptid, d.deptname from emp_info e,dept_info d where e.deptid=d.deptid and empid=#{empid}")
    EmpInfo selectBykeyFindDept(Integer empid);
    /**
     * delete by primary key
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer empid);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(EmpInfo record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(EmpInfo record);

    /**
     * select by primary key
     * @param empid primary key
     * @return object by primary key
     */
    EmpInfo selectByPrimaryKey(Integer empid);
    
    EmpInfo selectEmpDeptByPrimaryKey(Integer empid);
    
    List<EmpInfo> selectByLikeName(String empname);
    
    EmpInfo getEmpCertid(String empid);
    List<EmpInfo> selectEmpInfoList();

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(EmpInfo record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(EmpInfo record);
}