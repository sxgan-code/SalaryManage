package com.daniel.mappers;

import com.daniel.domain.AttendanceData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceDataMapper {
    @Select("select count(*) from attendance_data where absenteeismtime = #{absenteeismtime} ")
    int getAttendCount(String absenteeismtime);
    /**
     * delete by primary key
     *
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("empid") Integer empid, @Param("absenteeismtime") String absenteeismtime);
    
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(AttendanceData record);
    
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(AttendanceData record);
    
    /**
     * select by primary key
     *
     * @param empid primary key
     * @return object by primary key
     */
    AttendanceData selectByPrimaryKey(@Param("empid") Integer empid, @Param("absenteeismtime") String absenteeismtime);
    
    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(AttendanceData record);
    
    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(AttendanceData record);
    
    @Select("select * from attendance_data where empid=#{empid} and absenteeismtime=#{absenteeismtime}")
    AttendanceData selectByIdAndTime(@Param("empid") Integer empid, @Param("absenteeismtime") String absenteeismtime);
    
    List<AttendanceData> selectAttendList();
    
    int batchInsert(@Param("list") List<AttendanceData> list);
}