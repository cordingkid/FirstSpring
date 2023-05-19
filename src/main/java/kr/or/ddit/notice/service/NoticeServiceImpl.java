package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDao;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService{

	@Inject
	private INoticeDao noticeDao;
	
	@Override
	public ServiceResult insertNotice(NoticeVO noticeVo) {
		ServiceResult result = null;
		int affectRowNum = noticeDao.insertNotice(noticeVo);
		if(affectRowNum > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public NoticeVO selectNotice(int no) {
		noticeDao.incrementHit(no);
		return noticeDao.selectNotice(no);
	}
	@Override
	public ServiceResult deleteNotice(int no) {
		ServiceResult result = null;
		int affectRowNum = noticeDao.deleteNotice(no);
		if(affectRowNum > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int affectRowNum = noticeDao.updateNotice(noticeVO);
		if(affectRowNum > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeDao.selectNoticeCount(pagingVO);
	}
	
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeDao.selectNoticeList(pagingVO);
	}
	
	@Override
	public int allCount() {
		return noticeDao.allCount();
	}
}
