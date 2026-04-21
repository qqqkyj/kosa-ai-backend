package com.mycompany.backendapi.restcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller")
public class ControllerTest {
	@RequestMapping("/ui")
	public String ui() {
		return "ui";
	}
}
