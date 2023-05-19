package kr.or.ddit.main.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;

@Repository
public class MainDao implements IMainDao {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> selectBoardList() {
		return sqlSession.selectList("Main.selectBoardList");
	}
	
	@Override
	public List<BoardVO> selectNoticeList() {
		return sqlSession.selectList("Main.selectNoticeList");
	}
	
	@Override
	public List<BoardVO> selectFreeList() {
		return sqlSession.selectList("Main.selectFreeList");
	}

	@Override
	public int allCountN() {
		return sqlSession.selectOne("Main.allCountN");
	}

	@Override
	public int allCountB() {
		return sqlSession.selectOne("Main.allCountB");
	}

	@Override
	public int allCountF() {
		return sqlSession.selectOne("Main.allCountF");
	}
}
















