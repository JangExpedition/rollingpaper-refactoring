package com.sh.rollingpaper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class HelloRollingpaperController {

	@GetMapping("/")
	public String home() {
		return "forward:/index.jsp";
	}
}
