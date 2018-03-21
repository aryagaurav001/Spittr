package com.sia.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

	@RequestMapping(method=GET)
	public String home(Model model) {
		return "home";
	}
}
