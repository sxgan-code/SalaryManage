package com.daniel.mappers;

import com.daniel.domain.SalaryInfo;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SalaryInfoMapper {
    
    @Select("select * from salary_info")
    List<SalaryInfo> selectAllSalaryList();
    
    @Select("select sum(sum) from salary_info where distributiontime = #{distributiontime} ")
    BigDecimal getSalarySum(String distributiontime);
    
    @Select("select * from salary_info where distributiontime=#{distributiontime}")
    List<SalaryInfo> selectSalaryList(@Param("distributiontime") String distributiontime);
    /**
     * delete by primary key
     *
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("empid") Integer empid, @Param("distributiontime") String distributiontime);
    
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(SalaryInfo record);
    
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(SalaryInfo record);
    
    /**
     * select by primary key
     *
     * @param empid primary key
     * @return object by primary key
     */
    SalaryInfo selectByPrimaryKey(@Param("empid") Integer empid, @Param("distributiontime") String distributiontime);
    
    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SalaryInfo record);
    
    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SalaryInfo record);
    
    int batchInsert(@Param("list") List<SalaryInfo> list);
}