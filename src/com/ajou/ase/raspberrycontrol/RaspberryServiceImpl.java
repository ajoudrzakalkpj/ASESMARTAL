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
	
	public Object getObjectbyNumSeq(Object obj) throws SQLException {
		return this.dao.selectbyNumSeq(obj);
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
	
	public void removeRaspberryInfo(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateForRemovingRaspberryInfo(obj);
	}

	// save 서비스에 insert 추가 
	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insert(obj);
		return true;
	}

	public List getConfirmedList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getConfirmedListByStatus(obj);
	}	
	
	public List getUnconfirmedList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getUnconfirmedListByStatus(obj);
	}
	
	public List getUnconfirmedSAList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getUnconfirmedListBySerialNumber(obj);
	}	
	
	public List getSAListBySerialNumber(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getSAListBySerialNumber(obj);
	}
	
	public List getSAListByRelatedSeqNum(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getSAListByRelatedSeqNum(obj);
	}	
	
	public List getConfirmedListWithSSID(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getConfirmedListWithSSID(obj);
	}	

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void updateSSID(Object obj) throws SQLException{
		dao.updateSSID(obj);
	}
	
	public boolean confirmRaspberry(Object obj) throws SQLException{
		dao.confirmRaspberry(obj);
		return true;
	}

	

}
