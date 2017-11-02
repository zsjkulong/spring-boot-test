package com.zsj.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	/**
	 * 用户查询.
	 * 
	 * @return
	 */
	@RequestMapping("/userList")
	public String userInfo() {
		return "userInfo";
	}

	/**
	 * 用户添加;
	 * 
	 * @return
	 */
	@RequestMapping("/userAdd")
	 @RequiresPermissions("userInfo:add")//权限管理;  
	public String userInfoAdd() {
//		System.out.println("asdf");
		logger.info("asdf");
		return "userInfoAdd";
	}

	
}