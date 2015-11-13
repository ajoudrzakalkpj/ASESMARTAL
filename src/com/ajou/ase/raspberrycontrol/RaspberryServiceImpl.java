package com.ajou.ase.raspberrycontrol;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ajou.ase.raspberrycontrol.RaspberryDaoImpl;


@Service("raspberrycontrolService")
public class RaspberryServiceImpl implements com.ajou.ase.common.Service {
	
	@Resource(name = "raspberrycontrolDao")
	RaspberryDaoImpl dao;
	

	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}
	
	//10.26. 봉재 : ID 중복 확인을 위해 SQL에서 데이터 가져오는 로직
	public Object getObjectForNumSNcheck(Object obj) throws SQLException {
		return this.dao.selectForNumSNCheck(obj);
	}
	
	
	@Override
	public boolean edit(Object obj) throws SQLException {
		return true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	// save 서비스에 insert 추가 
	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insert(obj);
		return true;
	}
	// 10.9 봉재 : update로직 추가
	public boolean userinfoupdate(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(obj);
		return true;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void updateSSID(Object obj) throws SQLException{
		dao.updateSSID(obj);
	}
	
	public Object login(Object obj) throws SQLException {
		
		return dao.login(obj);
	}
	
	
	public boolean updateConfirmationInfo(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateConfirmationInfo(obj);
		return true;
	}
}
