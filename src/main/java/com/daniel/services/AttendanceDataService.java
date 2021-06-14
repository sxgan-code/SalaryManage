package com.daniel.services;

import java.util.List;
import com.daniel.domain.AttendanceData;

public interface AttendanceDataService {
    
    int getAttendCount(String absenteeismtime);
    
    AttendanceData selectByIdAndTime(Integer empid, String absenteeismtime);
    
    List<AttendanceData> selectAttendList();
    
    int insert(AttendanceData record);
    
    int insertSelective(AttendanceData record);
    
    int batchInsert(List<AttendanceData> list);
    
    int deleteByPrimaryKey(Integer empid, String absenteeismtime);
    
    AttendanceData selectByPrimaryKey(Integer empid, String absenteeismtime);
    
    int updateByPrimaryKeySelective(AttendanceData record);
    
    int updateByPrimaryKey(AttendanceData record);
}



