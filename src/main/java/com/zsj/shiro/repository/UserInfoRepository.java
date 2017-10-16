package com.zsj.shiro.repository;

import org.springframework.data.repository.CrudRepository;

import com.zsj.shiro.bean.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
	/**通过username查找用户信息;*/  
    public UserInfo findByUsername(String username);  
}
