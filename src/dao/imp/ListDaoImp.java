package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import bean.Lists;

import dao.ListDao;

public class ListDaoImp implements ListDao {
	public  Lists list;

	@Override
	// 生成list
	public boolean insert(int orderid, int goodsid, int count, Connection conn)
			throws SQLException {
		String sql = "insert into list (orderid, goodsid , count ) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderid);
		ps.setInt(2, goodsid);
		ps.setInt(3, count);
		int i = ps.executeUpdate();
		return i > 0;
	}

	@Override
	// 删除list
	public boolean delete(int orderid, Connection conn) throws SQLException {
		String sql = "delete from list where orderid = ? " ;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderid);
		int i = ps.executeUpdate();
		return i > 0;
	}

	// 根据订单号获得list信息
	@Override
	public boolean query(int orderid, Connection conn) throws SQLException {
		String sql = "select *from list where orderid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderid);
		ResultSet executeQuery = ps.executeQuery();
		while (executeQuery.next()) {
			Lists l = new Lists();
			l.setCount(executeQuery.getInt("count"));
			l.setGoodsid(executeQuery.getInt("goodsid"));
			l.setOrderid(executeQuery.getInt("orderid"));
			list = l;
		}
		return false;
	}

}
