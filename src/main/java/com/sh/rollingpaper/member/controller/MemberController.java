package com.sh.rollingpaper.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
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
	
	/**
	 * 로그인 객체 반환 메서드
	 */
	public Member getLoginMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member loginMember = (Member) authentication.getPrincipal();
		return loginMember;
	}
	
	/*
	 * 나를 제외한 모든 회원목록 조회
	 */
	@GetMapping("/memberList")
	public void memberList(Model model) {
		Member loginMember = getLoginMember();
		String name = loginMember.getName();
		List<Member> memberList = memberService.selectAllMemberWithoutMe(name);
		model.addAttribute("memberList", memberList);
	}
	
	@GetMapping("/myPage.do")
	public String myPage(@RequestParam int no, Model model) {
		List<Board> boardList = boardService.selectBoardList(no);
		model.addAttribute("boardList", boardList);
		return "member/myPage";
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
