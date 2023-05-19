package kr.or.ddit.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.main.dao.IMainDao;
import kr.or.ddit.vo.BoardVO;

@Service
public class MainServiceImpl implements IMainService {

	@Inject
	private IMainDao mainDao;
	
	@Override
	public List<BoardVO> selectBoardList() {
		return mainDao.selectBoardList();
	}
	
	@Override
	public List<BoardVO> selectNoticeList() {
		return mainDao.selectNoticeList();
	}
	
	@Override
	public List<BoardVO> selectFreeList() {
		return mainDao.selectFreeList();
	}

	@Override
	public int allCountN() {
		return mainDao.allCountN();
	}

	@Override
	public int allCountB() {
		return mainDao.allCountB();
	}

	@Override
	public int allCountF() {
		return mainDao.allCountF();
	}

}
