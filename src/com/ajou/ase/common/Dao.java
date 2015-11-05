package com.ajou.ase.common;

import java.sql.SQLException;
import java.util.List;

public interface Dao{
	public void insert(Object obj) throws SQLException;
	public Object select(Object obj) throws SQLException;
	public void update(Object obj) throws SQLException;
	public void delete(Object obj) throws SQLException;
	public List getList(Object obj) throws SQLException;
	public int getRowCount(Object obj) throws SQLException;
}