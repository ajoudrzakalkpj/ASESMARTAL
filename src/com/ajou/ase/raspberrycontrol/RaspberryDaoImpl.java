package com.ajou.ase.raspberrycontrol;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.ase.common.BaseDao;
import com.ajou.ase.common.Dao;
import com.ajou.ase.raspberrycontrol.Raspberry;


@Repository("raspberrycontrolDao")
public class RaspberryDaoImpl extends BaseDao implements Dao {

	
	// 10.9 봉재 : (확인 필요) select가 선택되면 반드시 여기로만 오기 때문에, select의 다른 로직을 사용하는 경우 조정이 필요함  
	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.ase.user.selectUserByUserID", obj);
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

		
	}

	// 10.9 봉재 : 업데이트 로직 추가
	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	public void updateConfirmationInfo(Object obj) throws SQLException {
		// TODO Auto-generated method stub

	}
	
	public Object login(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.ase.user.selectUserLogin", obj);
	}
	
	public void updateSSID(Object obj) throws SQLException {
		
	}
	
	

}
