package dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface ListDao {
//？？？？？？、用来干嘛的

	
	//新增list
	public boolean insert(int orderid,int  goodsid,int count,Connection conn)throws SQLException;
//删除list
	public boolean delete(int orderid, Connection conn)throws SQLException;
//根据订单号查询list
	boolean query(int orderid, Connection conn) throws SQLException;
}
