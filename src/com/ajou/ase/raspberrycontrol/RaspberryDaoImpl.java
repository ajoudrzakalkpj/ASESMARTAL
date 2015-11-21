package com.ajou.ase.raspberrycontrol;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.ase.board.Board;
import com.ajou.ase.common.BaseDao;
import com.ajou.ase.common.Dao;
import com.ajou.ase.raspberrycontrol.Raspberry;
import com.ajou.ase.raspberrycontrol.RaspberrySA;


@Repository("raspberrycontrolDao")
public class RaspberryDaoImpl extends BaseDao implements Dao {

	
	// 10.9 봉재 : (확인 필요) select가 선택되면 반드시 여기로만 오기 때문에, select의 다른 로직을 사용하는 경우 조정이 필요함  
	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.ase.user.selectUserByUserID", obj);
	}
	
	public Object selectbyNumSeq(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.ase.raspberrycontrol.selectRaspberryByNumSeq", obj);
	}
	
	
	// 
	public Object selectForNumSNCheck(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.ase.raspberrycontrol.selectCheckSNBySN", obj);
	}

	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
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
		getSqlMapClientTemplate().insert("com.ajou.ase.raspberrycontrol.InsertRaspberryInformation", obj);
		
	}

	// 10.9 봉재 : 업데이트 로직 추가
	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	public void updateForRemovingRaspberryInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.ase.raspberrycontrol.updateForRemovingRaspberryInfo", obj);
	}
	
	public void updateSSID(Object obj) throws SQLException {
		
	}
	
	
	public void confirmRaspberry(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.ase.raspberrycontrol.confirmRaspberry", obj);
	}
	
	public List<Raspberry> getConfirmedListByStatus(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<Raspberry>)getSqlMapClientTemplate().queryForList("com.ajou.ase.raspberrycontrol.selectConfirmedList", obj);
	} 
	
	public List<Raspberry> getUnconfirmedListByStatus(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<Raspberry>)getSqlMapClientTemplate().queryForList("com.ajou.ase.raspberrycontrol.selectUnconfirmedList", obj);
	}
	
	public List<RaspberrySA> getUnconfirmedListBySerialNumber(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<RaspberrySA>)getSqlMapClientTemplate().queryForList("com.ajou.ase.raspberrycontrol.selectUnconfirmedListbySerialNumber", obj);
	} 
	
	public List<RaspberrySA> getSAListBySerialNumber(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<RaspberrySA>)getSqlMapClientTemplate().queryForList("com.ajou.ase.raspberrycontrol.selectSAListbySerialNumber", obj);
	}
	
	public List<RaspberrySA> getSAListByRelatedSeqNum(Object obj) throws SQLException {
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		return (List<RaspberrySA>)getSqlMapClientTemplate().queryForList("com.ajou.ase.raspberrycontrol.selectSAListbyRelatedSeqNum", obj);
	}	

}
