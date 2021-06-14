package com.daniel.mappers;

import com.daniel.domain.SanctionData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SanctionDataMapper {
    
    @Select("select * from sanction_data")
    List<SanctionData> selectSanctionList();
    
    /**
     * delete by primary key
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("empid") Integer empid, @Param("sanctiontime") String sanctiontime);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(SanctionData record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(SanctionData record);

    /**
     * select by primary key
     * @param empid primary key
     * @return object by primary key
     */
    SanctionData selectByPrimaryKey(@Param("empid") Integer empid, @Param("sanctiontime") String sanctiontime);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SanctionData record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SanctionData record);
}