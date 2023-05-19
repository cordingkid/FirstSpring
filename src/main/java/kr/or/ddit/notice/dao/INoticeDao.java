package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDao {

	public int insertNotice(NoticeVO noticeVo);

	public NoticeVO selectNotice(int no);

	public void incrementHit(int no);

	public int deleteNotice(int no);

	public int updateNotice(NoticeVO noticeVO);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public int allCount();
}
