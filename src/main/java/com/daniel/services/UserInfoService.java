package com.daniel.services;

import com.daniel.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);
    
    List<UserInfo> selectUserInfoList();
    
    List<UserInfo> selectByLikeName(String name);
    
    UserInfo selectByPrimaryKey(Integer id);
    
    UserInfo selectByName(String name);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

}
