package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {

	@Inject
	private IFreeService freeService;

	@RequestMapping(value = "/list.do")
	public String noticeList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = freeService.selectFreeCount(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		List<FreeVO> dataList = freeService.selectFreeList(pagingVO);
		
		int cnt = freeService.allCount();
		model.addAttribute("all",cnt);
		pagingVO.setDataList(dataList);
		model.addAttribute("paging", pagingVO);
		return "free/list";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insertFree() {
		return "free/form";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String freeInsert(FreeVO freeVO, Model model) {
		String goPage = ""; // 페이지를 담을 공간

		Map<String, Object> errors = new HashMap<String, Object>(); // 에러를 담을 공간

		if (StringUtils.isBlank(freeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if (StringUtils.isBlank(freeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요!");
		}

		if (errors.size() > 0) { // 에러가 발생
			model.addAttribute("errors", errors);
			model.addAttribute("free", freeVO);
			goPage = "free/form";
		} else { // 에러가 발생하지 않고 정상적인 데이터가 넘어옴
			freeVO.setBoWriter("worbs");
			ServiceResult result = freeService.insertFree(freeVO);
			if (result.equals(ServiceResult.OK)) {
				goPage = "redirect:/free/detail.do?boNo=" + freeVO.getBoNo();
			} else {
				errors.put("msg", "서버에러! 다시 시도해주세요!");
				model.addAttribute("errors", errors);
				goPage = "free/form";
			}
		}
		return goPage;
	}

	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String freeDetail(int boNo, Model model) {
		String goPage = "";
		FreeVO freeVO = freeService.selectFree(boNo);
		if(freeVO == null) {
			goPage = "redirect:/free/list.do";
		}else {
			goPage = "free/view";
			model.addAttribute("free", freeVO);
		}
		return goPage;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String freeUpdateForm(int boNo, Model model) {
		String goPage = "";
		FreeVO freeVO = freeService.selectFree(boNo);
		if(freeVO == null) {
			goPage = "redirect:/free/list.do";
		}else {
			model.addAttribute("free", freeVO);
			model.addAttribute("status", "u"); // u : update(수정)
			goPage = "free/form";
		}
		return goPage;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO freeVO, Model model) {
		String goPage = "";
		ServiceResult result = freeService.updateFree(freeVO);
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?boNo=" + freeVO.getBoNo();
		} else {
			model.addAttribute("free", freeVO);
			model.addAttribute("status", "u");
			goPage = "free/form";
		}
		return goPage;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String freeDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = freeService.deleteFree(boNo);
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		} else {
			goPage = "redirect:/free/detail.do?boNo=" + boNo;
		}
		return goPage;
	}
}
