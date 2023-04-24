package com.sh.rollingpaper.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.rollingpaper.board.model.dto.Board;
import com.sh.rollingpaper.board.model.service.BoardService;
import com.sh.rollingpaper.member.model.dto.Member;
import com.sh.rollingpaper.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
@SessionAttributes({"loginMember"})
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	@GetMapping("/boardList.do")
	public ModelAndView boardList(@RequestParam int no, ModelAndView mav) {
		List<Board> boardList = boardService.selectBoardList(no);
		Member member = memberService.selectOneMemberByNo(no);
		mav.addObject("owner", member);
		mav.addObject("boardList", boardList);
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@PostMapping("/searchBoardList.do")
	public String searchBoardList(@RequestParam String searchName, Model model, RedirectAttributes redirectAttr) {
		Member loginMember = (Member) model.getAttribute("loginMember");
		List<Member> memberList = memberService.selectAllMember(loginMember);
		if(searchName.equals(loginMember.getName())) {
			redirectAttr.addAttribute("no", loginMember.getNo());
			return "redirect:/member/myPage.do";
		}
		for(Member member : memberList) {
			if(searchName.equals(member.getName())) {
				redirectAttr.addAttribute("no", member.getNo());
				return "redirect:/board/boardList.do";
			}
		}
		redirectAttr.addAttribute("msg", "없는 학생입니다.");
		return "redirect:/member/memberList.do";
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(Board board, RedirectAttributes redirectAttr) {
		log.debug("board = {}", board);
		int result = boardService.insertBoard(board);
		redirectAttr.addFlashAttribute("msg", "게시글 등록 성공!");
		return "redirect:/member/memberList.do";
	}
	
	@GetMapping("/boardDetail.do")
	public String boardDetail(@RequestParam int no, Model model) {
		Board board = boardService.selectOneBoard(no);
		model.addAttribute("board", board);
		Member loginMember = (Member) model.getAttribute("loginMember");
		
		if(board.getWriter().equals(loginMember.getName())) {
			return "/board/boardDetail";
		}
		
		if(board.getOwnerNo() == loginMember.getNo()) {
			return "/board/boardDetail";
		}
		
		model.addAttribute("msg", "본인 페이지가 아닙니다. 돌아가세요^^");
		return "/member/memberList";
	}
	
	@PostMapping("deleteBoard.do")
	public String deleteBoard(@RequestParam int no, RedirectAttributes redirectAttr) {
		int result = boardService.deleteBoard(no);
		redirectAttr.addFlashAttribute("msg", "게시물이 성공적으로 삭제됐습니다.");
		return "redirect:/member/memberList.do";
	}
	
	@PostMapping("/updateForm.do")
	public String updateBoard(Board board, RedirectAttributes redirectAttr) {
		int result = boardService.updateBoard(board);
		redirectAttr.addAttribute("no", board.getNo());
		redirectAttr.addFlashAttribute("msg", "게시물이 성공적으로 수정됐습니다.");
		return "redirect:/board/boardDetail.do";
	}
}
