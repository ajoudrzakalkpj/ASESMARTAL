package com.ajou.ase.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ajou.ase.common.BaseDao;
import com.ajou.ase.common.Dao;
import com.ajou.ase.common.RequestParameter;


@Repository("boardDao")
public class BoardDaoImpl extends BaseDao implements Dao {
 
	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.ase.board.selectBoardBySeq", obj);
	}

	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("com.ajou.ase.board.deleteBoardInfo", obj);
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		return 0;
	}

	@Override
	public void insert(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		getSqlMapClientTemplate().insert("com.ajou.ase.user.InsertBoardInformation", obj);
	}

	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.ase.board.updateboardInfo", obj);
	}
	
	public int insertCheck(Object obj) throws SQLException {
		return (int)getSqlMapClientTemplate().insert("com.ajou.ase.board.InsertBoardInformation", obj);
	}                
	
	
	public List<Board> getListByPage(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<Board>)getSqlMapClientTemplate().queryForList("com.ajou.ase.board.selectListByPage", obj);
	} 

	public List<Board> getUnfinishedPostList(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<Board>)getSqlMapClientTemplate().queryForList("com.ajou.ase.board.selectUnfinishedPost", obj);
	} 
	
	public int updateCheck(Object obj) throws SQLException {
		return (int)getSqlMapClientTemplate().update("com.ajou.ase.board.updateBoardInfo", obj);
	}

	public void updateUserBoardCount(String boardWriter) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.ase.board.updateUserBoardCount", boardWriter);
	}
	
	public void updateUserBoardCountFall(String boardWriter) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.ase.board.updateUserBoardCountFall", boardWriter);
	}


	public int insertCommentCheck(Object obj) {
		// TODO Auto-generated method stub
		return (int)getSqlMapClientTemplate().update("com.ajou.ase.board.InsertBoardCommentInformation", obj);
	}
	
	
	public List<Board> getCommentList(String boardUpper) throws SQLException {
		return (List<Board>)getSqlMapClientTemplate().queryForList("com.ajou.ase.board.selectCommentList", boardUpper);
	}

	public void deleteComment(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("com.ajou.ase.board.deleteCommentBoardInfo", obj);
	}

	public void deleteStep1(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("com.ajou.ase.board.deleteCommentRelatedToCommentInfo", obj);
	}

	public void deleteAllComment(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("com.ajou.ase.board.deleteCommentRelatedToBoardInfo", obj);
	}

	public void updateHits(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.ase.board.updateBoardHits", obj);
	}

	public int boardCount(Object obj) {
		// TODO Auto-generated method stub
		return (int) getSqlMapClientTemplate().queryForObject("com.ajou.ase.board.selectRowCount", obj);
	}

	public List<Board> boardCommentNumber(Map<String, Integer> param) {
		// TODO Auto-generated method stub
		return (List<Board>)getSqlMapClientTemplate().queryForList("com.ajou.ase.board.selectListCntByPage",param);
	}

	public void boardSetRecommand(RequestParameter rp) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.secure.ase.updateBoardRecommadation", rp);
	} 

}
