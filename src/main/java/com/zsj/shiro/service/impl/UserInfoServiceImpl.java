package com.zsj.shiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zsj.shiro.bean.UserInfo;
import com.zsj.shiro.repository.UserInfoRepository;
import com.zsj.shiro.service.UserInfoService;

@Service 
public class UserInfoServiceImpl implements UserInfoService {

	@Resource  
    private UserInfoRepository userInfoRepository;  
     
    @Override  
    public UserInfo findByUsername(String username) {  
       System.out.println("UserInfoServiceImpl.findByUsername()");  
       return userInfoRepository.findByUsername(username);  
    }  

}
