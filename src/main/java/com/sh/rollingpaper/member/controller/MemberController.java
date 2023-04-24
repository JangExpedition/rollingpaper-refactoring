package com.sh.rollingpaper.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.rollingpaper.board.model.dto.Board;
import com.sh.rollingpaper.board.model.service.BoardService;
import com.sh.rollingpaper.member.model.dto.Member;
import com.sh.rollingpaper.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/memberLogin.dp")
	public String memberLogin() {
		return "/";
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(Member member, Model model, RedirectAttributes redirectAttr) {
		Member loginMember = memberService.selectOneMember(member.getName());
		
//		if(loginMember != null && passwordEncoder.matches(member.getPassword(), loginMember.getPassword())) {
//			model.addAttribute("loginMember", loginMember);
//			return "redirect:/member/memberList.do";
//		}
//		else{
//			redirectAttr.addFlashAttribute("msg", "아이디 비밀번호가 일치하지 않습니다.");
			return "redirect:/";
//		}
	}
	
	@GetMapping("/memberList.do")
	public void memberList(Model model) {
		Member loginMember = (Member) model.getAttribute("loginMember");
		List<Member> memberList = memberService.selectAllMember(loginMember);
		log.debug("memberList = {}", memberList);
		model.addAttribute("memberList", memberList);
	}
	
	@GetMapping("/myPage.do")
	public String myPage(@RequestParam int no, Model model) {
		List<Board> boardList = boardService.selectBoardList(no);
		model.addAttribute("boardList", boardList);
		return "member/myPage";
	}
	
	@GetMapping("/logout.do")
	public String logout(SessionStatus status) {
		if(!status.isComplete())
			status.setComplete();
		
		return "redirect:/";
	}
	
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(
			@ModelAttribute("loingMember") Member member,
			RedirectAttributes redirectAttr
			) {
//		member.setPassword(passwordEncoder.encode(member.getPassword()));
		log.debug("loginMember = {}", member);
		int result = memberService.updateMember(member);
		redirectAttr.addFlashAttribute("msg", "비밀번호 수정 성공!");
		redirectAttr.addAttribute("no", member.getNo());
		return "redirect:/member/myPage.do";
	}
	
	@ResponseBody
	@GetMapping("/checkPassword.do")
	public Map<String, Object> checkPassword(@RequestParam String password, Model model) {
		Map<String, Object> map = new HashMap<>();
		Member loginMember = (Member) model.getAttribute("loginMember");
//		boolean available = passwordEncoder.matches(password, loginMember.getPassword());
//		map.put("available", available);
		return map;
	}
	
	@PostMapping("/resetPwd.do")
	public String resetPwd(@RequestParam int no, RedirectAttributes redirectAttr) {
		Member member = memberService.selectOneMemberByNo(no);
		member.setPassword("1234");
//		member.setPassword(passwordEncoder.encode(member.getPassword()));
		log.debug("loginMember = {}", member);
		int result = memberService.updateMember(member);
		redirectAttr.addFlashAttribute("msg", "비밀번호 수정 성공!");
		return "redirect:/member/memberList.do";
	}
	
}
