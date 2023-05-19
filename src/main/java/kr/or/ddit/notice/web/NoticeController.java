package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Inject
	private INoticeService noticeService;

	@RequestMapping(value = "/list.do")
	public String noticeList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = noticeService.selectNoticeCount(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		
		int cnt = noticeService.allCount();
		model.addAttribute("all",cnt);
		
		pagingVO.setDataList(dataList);
		model.addAttribute("paging", pagingVO);
		return "notice/list";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insert() {
		return "notice/form";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insert(NoticeVO noticeVo, Model model) {
		String goPage = "";
		noticeVo.setWriter("worbs");

		Map<String, Object> errors = new HashMap<String, Object>();

		if (StringUtils.isBlank(noticeVo.getSubject())) {
			errors.put("subject", "제목을 입력해주세요.");
		}

		if (StringUtils.isBlank(noticeVo.getContent())) {
			errors.put("content", "내용을 입력해주세요.");
		}

		if (errors.size() > 0) { // 오류 있음
			model.addAttribute("errors", errors);
			model.addAttribute("notice", noticeVo);
			goPage = "notice/form";
		} else {
			ServiceResult result = noticeService.insertNotice(noticeVo);
			if (result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?no=" + noticeVo.getNo();
			} else {
				errors.put("msg", "서버에러! 다시 시도해주세요!");
				model.addAttribute("errors", errors);
				goPage = "notice/form";
			}
		}
		return goPage;
	}

	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(int no, Model model) {
		String goPage = "";
		NoticeVO noticeVO = noticeService.selectNotice(no);
		if(noticeVO == null) {
			goPage = "redirect:/notice/list.do";
		}else {
			goPage = "notice/view";
			model.addAttribute("notice", noticeVO);
		}
		return goPage;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String delete(int no, Model model) {
		String goPage = "";
		Map<String, Object> errors = new HashMap<String, Object>();
		ServiceResult result = noticeService.deleteNotice(no);
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/list.do";
		} else {
			errors.put("msg", "서버에러 다시 시도해주세요!");
			model.addAttribute("errors", errors);
			goPage = "redirect:/notice/detail.do?no=" + no;
		}
		return goPage;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateNotice(int no, Model model) {
		String goPage = "";
		NoticeVO noticeVO = noticeService.selectNotice(no);
		if(noticeVO == null) {
			goPage = "redirect:/notice/list.do";
		}else {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		return goPage;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String updateNotice(NoticeVO noticeVO, Model model) {
		String goPage = "";

		Map<String, Object> errors = new HashMap<String, Object>();

		if (StringUtils.isBlank(noticeVO.getSubject())) {
			errors.put("subject", "제목을 입력해주세요.");
		}

		if (StringUtils.isBlank(noticeVO.getContent())) {
			errors.put("content", "내용을 입력해주세요.");
		}

		if (errors.size() > 0) { // 오류 있음
			model.addAttribute("errors", errors);
			model.addAttribute("notice", noticeVO);
			goPage = "notice/form";
		} else {
			ServiceResult result = noticeService.updateNotice(noticeVO);
			if (result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?no="+noticeVO.getNo();
			} else {
				errors.put("msg", "서버에러 다시 시도해주세요!");
				model.addAttribute("errors", errors);
				goPage = "redirect:/notice/update.do?no=" + noticeVO.getNo();
			}
		}
		return goPage;
	}
}





















