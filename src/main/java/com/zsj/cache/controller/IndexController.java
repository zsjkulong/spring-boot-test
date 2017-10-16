package com.zsj.cache.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/index")
	public ResponseEntity index(HttpSession httpSession) {
		httpSession.setAttribute("user", "helloword");
		return ResponseEntity.ok("ok");
	}

	@GetMapping("/helloword")
	public ResponseEntity hello(HttpSession httpSession) {
		return ResponseEntity.ok(httpSession.getAttribute("user"));
	}
}
