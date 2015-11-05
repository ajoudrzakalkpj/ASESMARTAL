package com.ajou.ase.common;

import java.sql.SQLException;
import java.util.List;

public interface Service{
	public boolean save(Object obj) throws SQLException;
	public Object getObject(Object obj) throws SQLException;
	public boolean edit(Object obj) throws SQLException;
	public void remove(Object obj) throws SQLException;
	public int getRowCount(Object obj) throws SQLException;
	public List getList(Object obj) throws SQLException;
	public boolean delete(Object obj) throws SQLException;
}