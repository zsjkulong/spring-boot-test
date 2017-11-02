package com.zsj.async;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	@Resource
	Task task1;
	
	@RequestMapping("/task1")
	@Async
    public String task1() throws Exception{
	   System.out.println("task1 output");
       task1.doTaskOne();
       task1.doTaskTwo();
       task1.doTaskThree();
       return "task1";
    }
	
	@RequestMapping("/task2")
	@Async
    public String task2() throws Exception{
	   System.out.println("task2 output");
       return "task1";
    }
}
