package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.daniel.domain.AttendanceData;
import com.daniel.mappers.AttendanceDataMapper;
import com.daniel.services.AttendanceDataService;

@Service
public class AttendanceDataServiceImpl implements AttendanceDataService {
    
    
    @Resource
    private AttendanceDataMapper attendanceDataMapper;
    
    @Override
    public int getAttendCount(String absenteeismtime) {
        return attendanceDataMapper.getAttendCount(absenteeismtime);
    }
    
    public AttendanceData selectByIdAndTime(Integer empid, String absenteeismtime) {
        return attendanceDataMapper.selectByIdAndTime(empid, absenteeismtime);
    }
    
    public List<AttendanceData> selectAttendList() {
        return attendanceDataMapper.selectAttendList();
    }
    
    @Override
    public int insert(AttendanceData record) {
        return attendanceDataMapper.insert(record);
    }
    
    @Override
    public int insertSelective(AttendanceData record) {
        return attendanceDataMapper.insertSelective(record);
    }
    
    @Override
    public int batchInsert(List<AttendanceData> list) {
        return attendanceDataMapper.batchInsert(list);
    }
    
    @Override
    public int deleteByPrimaryKey(Integer empid, String absenteeismtime) {
        return attendanceDataMapper.deleteByPrimaryKey(empid, absenteeismtime);
    }
    
    @Override
    public AttendanceData selectByPrimaryKey(Integer empid, String absenteeismtime) {
        return attendanceDataMapper.selectByPrimaryKey(empid, absenteeismtime);
    }
    
    @Override
    public int updateByPrimaryKeySelective(AttendanceData record) {
        return attendanceDataMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(AttendanceData record) {
        return attendanceDataMapper.updateByPrimaryKey(record);
    }
}



