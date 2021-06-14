package com.daniel.mappers;

import com.daniel.domain.OvertimeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OvertimeDataMapper {
    @Select("select * from overtime_data")
    List<OvertimeData> selectOvertimeList();
    /**
     * delete by primary key
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("empid") Integer empid, @Param("overtimemonth") String overtimemonth);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(OvertimeData record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(OvertimeData record);

    /**
     * select by primary key
     * @param empid primary key
     * @return object by primary key
     */
    OvertimeData selectByPrimaryKey(@Param("empid") Integer empid, @Param("overtimemonth") String overtimemonth);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OvertimeData record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OvertimeData record);
}