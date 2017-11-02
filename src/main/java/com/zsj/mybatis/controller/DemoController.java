package com.zsj.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.zsj.mybatis.bean.Demo;
import com.zsj.mybatis.service.DemoService;

@RestController
public class DemoController {
	@Autowired
	private DemoService demoService;

	@RequestMapping("/likeName")
	public List<Demo> likeName(String name) {
		PageHelper.startPage(2, 1);
		return demoService.likeName(name);
	}

	@RequestMapping("/save")
	public void save() {
		// PageHelper.startPage(2,1);
		Demo demo = new Demo();
		demo.setName("a四");
		demoService.save(demo);
	}
	
	@RequestMapping("/select")
	public List<Demo> select(@RequestParam(value="name",required=false) String name) {
		// PageHelper.startPage(2,1);
		Demo demo = new Demo();
		demo.setName(name);
		return demoService.select(demo);
	}
	
	
	@RequestMapping("/save0")  
    public Demo save0(){  
       Demo demo = new Demo();  
       demo.setName("张三save0");  
       demoService.save0(demo);  
       return demo;  
    }  
}
