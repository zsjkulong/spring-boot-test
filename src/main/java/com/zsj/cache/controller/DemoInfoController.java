package com.zsj.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsj.cache.bean.DemoInfo;
import com.zsj.cache.service.DemoInfoService;

@Controller
@EnableAutoConfiguration
public class DemoInfoController {
	@Autowired
	DemoInfoService demoInfoService;

	@RequestMapping("/test")
	public @ResponseBody String test() {
		DemoInfo loaded = demoInfoService.findById(1);
		System.out.println("loaded=" + loaded);
		DemoInfo cached = demoInfoService.findById(1);
		System.out.println("cached=" + cached);
		loaded = demoInfoService.findById(2);
		System.out.println("loaded2=" + loaded);
		return "ok";
	}

	@RequestMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable long id) {
		demoInfoService.deleteFromCache(id);
		return "ok";
	}

	@RequestMapping("/test1")
	public @ResponseBody String test1() {
		demoInfoService.test();
		System.out.println("DemoInfoController.test1()");
		return "ok";
	}
}
