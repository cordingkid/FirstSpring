package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.main.service.IMainService;
import kr.or.ddit.vo.BoardVO;

@Controller
public class MainController {

	@Inject
	private IMainService mainService;
	
	@RequestMapping(value = { "/", "/main.do" }, method = RequestMethod.GET)
	public String main(Model model) {
		// 일반 게시판 게시글 총 5개의 정보를 가져와서 메인화면에  뿌릴거임
		List<BoardVO> boardList = mainService.selectBoardList();
		
		model.addAttribute("boardList", boardList);
		
		List<BoardVO> noticeList = mainService.selectNoticeList();
		
		model.addAttribute("noticeList", noticeList);
		
		List<BoardVO> freeList = mainService.selectFreeList();
		
		model.addAttribute("freeList", freeList);
		
		model.addAttribute("cntN",mainService.allCountN());
		model.addAttribute("cntB",mainService.allCountB());
		model.addAttribute("cntF",mainService.allCountF());
		
		return "main";
	}
}
