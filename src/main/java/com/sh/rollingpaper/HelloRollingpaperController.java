package com.sh.rollingpaper;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloRollingpaperController {

	@GetMapping("/")
	public String home(@RequestParam(required = false) String msg, Model model) {
		log.debug("HelloRollingpaperController!!!, msg = {}", msg);
		if(msg != null) {
			model.addAttribute("msg", msg);
		}
		return "forward:/index.jsp";
	}
}
