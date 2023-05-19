package kr.or.ddit.main.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IMainDao {

	public List<BoardVO> selectBoardList();

	public List<BoardVO> selectNoticeList();

	public List<BoardVO> selectFreeList();

	public int allCountN();
	public int allCountB();
	public int allCountF();
}
