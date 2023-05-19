package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeDao implements INoticeDao {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertNotice(NoticeVO noticeVo) {
		return sqlSession.insert("Notice.insertNotice", noticeVo);
	}

	@Override
	public void incrementHit(int no) {
		sqlSession.update("Notice.incrementHit", no);
	}
	
	@Override
	public NoticeVO selectNotice(int no) {
		return sqlSession.selectOne("Notice.selectNotice", no);
	}
	
	@Override
	public int deleteNotice(int no) {
		return sqlSession.delete("Notice.deleteNotice", no);
	}
	
	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("Notice.updateNotice", noticeVO);
	}
	
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectOne("Notice.selectNoticeCount", pagingVO);
	}
	
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectNoticeList", pagingVO);
	}
	
	@Override
	public int allCount() {
		return sqlSession.selectOne("Notice.allCount");
	}
}
