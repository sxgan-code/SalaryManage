package com.daniel.mappers;

import com.daniel.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectUserInfoList();
    
    UserInfo selectByPrimaryKey(Integer id);
    
    UserInfo selectByName(String name);
    
    List<UserInfo> selectByLikeName(String name);
    
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}