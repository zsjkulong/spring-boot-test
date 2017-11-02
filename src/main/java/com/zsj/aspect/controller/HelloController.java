package com.zsj.aspect.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/hello")
	public String hello(String name, int state) {
		return "name " + name + "---" + state;
	}
}
