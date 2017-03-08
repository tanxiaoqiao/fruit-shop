package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Lists;
import bean.Orders;


public interface OrdersDao {
	
	//插入订单
	boolean insert(int userid, double totalmoney, Connection conn)
			throws SQLException;
	//查询所有订单
	List<Orders> query(int userid, Connection conn) throws SQLException;
	//删除订单
	boolean delete(int orderid, Connection conn) throws SQLException;
	//根据订单查询所有信息
	boolean queryAll(int orderid, Connection conn) throws SQLException;
	List<Lists> queryO( Connection conn) throws SQLException;

}
