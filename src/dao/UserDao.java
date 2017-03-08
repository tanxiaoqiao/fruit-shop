package dao;

import java.sql.Connection;
import java.sql.SQLException;



public interface UserDao {
	/**
	 * 用户的一些基本功能 ： 1注册用户 insert
	 * 
	 * @param
	 */

	

	public boolean insert(String name, String password, Connection conn)
			throws SQLException;
	//查询用户是否存在
	public boolean queryName(String username, Connection conn) throws SQLException;
//登录
	public boolean queryAll(String username, String password, Connection conn)
			throws SQLException;
//购买时，查看余额
	public boolean updateMoney(int userid, double total, Connection conn) throws SQLException ;
	
	
}
