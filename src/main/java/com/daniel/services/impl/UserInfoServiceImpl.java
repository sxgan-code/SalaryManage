package com.daniel.services.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.daniel.domain.UserInfo;
import com.daniel.mappers.UserInfoMapper;
import com.daniel.services.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<UserInfo> selectByLikeName(String name) {
        return userInfoMapper.selectByLikeName(name);
    }
    
    @Override
    public List<UserInfo> selectUserInfoList() {
        
        return userInfoMapper.selectUserInfoList();
    }
    @Override
    public UserInfo selectByName(String name){
        return userInfoMapper.selectByName(name);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

}
