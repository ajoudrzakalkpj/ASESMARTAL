package com.ajou.ase.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ajou.ase.common.RequestParameter;

@Service("boardService")
public class BoardServiceImpl implements com.ajou.ase.common.Service {
	
	@Resource(name = "boardDao")
	BoardDaoImpl dao;
	
	// 10.9 봉재 : (확인 필요) select가 선택되면 "com.ajou.secure.user.selectUserByUserID"만 선택되기 때문에,
	// select의 다른 로직을 사용하는 경우 조정이 필요함 
	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}

	@Override
	public boolean edit(Object obj) throws SQLException {
		return true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getListByPage(obj);
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.delete(obj);

	}

	// save 서비스에 insert 추가 
	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insertCheck(obj);
		return true;
	}
	// 10.9 봉재 : update로직 추가
	public boolean update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(obj);
		return true;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public int saveCheck(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertCheck(obj);
	}
	
	public int updateCheck(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updateCheck(obj);
	}

	public void updateUserBoardCount(String boardWriter) {
		// TODO Auto-generated method stub
		dao.updateUserBoardCount(boardWriter);
	}
	
	public void updateUserBoardCountFall(String boardWriter) {
		// TODO Auto-generated method stub
		dao.updateUserBoardCountFall(boardWriter);
	}

	public int saveCheckComment(Object obj) {
		// TODO Auto-generated method stub
		return dao.insertCommentCheck(obj);
	}
	
	public List<Board> getCommentList(String boardUpper) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getCommentList(boardUpper);
	}

	public void removeStep1(Object obj) {
		// TODO Auto-generated method stub
		dao.deleteStep1(obj);
	}

	public void removeAllComment(Object obj) {
		// TODO Auto-generated method stub
		dao.deleteAllComment(obj);
	}

	public void inscreaseHits(Object obj) {
		// TODO Auto-generated method stub
		dao.updateHits(obj);
	}

	public int getBoardCount(Object obj) {
		return dao.boardCount(obj);
	}

	public List<Board> getBoardCommentNumber(
			Map<String, Integer> param) {
		// TODO Auto-generated method stub
		return dao.boardCommentNumber(param);
	}

	public void setRecommand(RequestParameter rp) {
		// TODO Auto-generated method stub
		dao.boardSetRecommand(rp);
	}
	
	public List getUnfinishedPostList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getUnfinishedPostList(obj);
	}

}
