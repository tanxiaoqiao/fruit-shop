package dao.imp;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.List;


import bean.Lists;
import bean.Orders;

import dao.OrdersDao;

public class OrderDaoImp implements OrdersDao {
	public Orders order;
	List<Lists> manyList = new ArrayList<Lists>();

	@Override
	// ���Ӷ����ķ���
	public boolean insert(int userid, double totalmoney, Connection conn)
			throws SQLException {
		String sql = "insert into orders (userid, totalmoney,orderdate) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, userid);
		ps.setDouble(2, totalmoney);
		ps.setTimestamp(3, currentTime());
		int i = ps.executeUpdate();
		ResultSet res = ps.getGeneratedKeys();
		if (res.next()) {
			int orderid = res.getInt(1);// ?????????????????�����������������������
			queryAll(orderid, conn);// ��ö�����Ϣ������
		}
		return i > 0;
	}

	// ��ѯ�����ķ���
	@Override
	public boolean queryAll(int orderid, Connection conn) throws SQLException {
		String sql = "select * from orders where orderid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderid);
		ResultSet executeQuery = ps.executeQuery();
		if (executeQuery.next()) {
			Orders or = new Orders();
			or.setOrderdate(executeQuery.getTimestamp("orderdate"));
			or.setOrderid(executeQuery.getInt("orderid"));
			or.setTotalmoney(executeQuery.getDouble("totalmoney"));
			or.setUserid(executeQuery.getInt("userid"));
			order = or;
			System.out.println(order);
			return true;
		}
		return false;

	}

	// �û�����û�id��ѯ
	@Override
	public List<Orders> query(int userid, Connection conn) throws SQLException {
		List<Orders> manyOrder = null;
		ResultSet executeQuery = null;
		String sql = "select*from orders where userid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userid);
		executeQuery = ps.executeQuery();
		manyOrder = new ArrayList<Orders>();
		while (executeQuery.next()) {
			Orders or = new Orders();
			or.setDescription(executeQuery.getString("description"));
			or.setOrderdate(executeQuery.getTimestamp("orderdate"));
			or.setOrderid(executeQuery.getInt("orderid"));
			or.setTotalmoney(executeQuery.getDouble("totalmoney"));
			or.setUserid(executeQuery.getInt("userid"));
			manyOrder.add(or);

		}

		return manyOrder;

	}

	// admin��ѯ����
	@Override
	public List<Lists> queryO(Connection conn) throws SQLException {
		ResultSet executeQuery = null;
		String sql = 
				"select  orders.orderid , goodsname , goodsprice,list.count," +
				"orders.totalmoney " +
				" from good,orders,list " +				
				" where good.goodsid=list.goodsid and list.orderid = orders.orderid";
		executeQuery = conn.createStatement().executeQuery(sql);
		manyList = new ArrayList<Lists>();
		while (executeQuery.next()) {
			Lists l = new Lists();
			l.setOrderid(executeQuery.getInt("orderid"));
			l.setTotalMoney(executeQuery.getDouble("totalmoney"));
			l.setGoodsName(executeQuery.getString("goodsname"));
			l.setGoodsPrice(executeQuery.getDouble("goodsprice"));
			l.setCount(executeQuery.getInt("count"));
			manyList.add(l);
		}
		return manyList;
	}

	public Timestamp currentTime() {

		Timestamp tt = new Timestamp(System.currentTimeMillis());
		return tt;

	}

	@Override
	// ɾ�����
	public boolean delete(int orderid, Connection conn) throws SQLException {
		String sql = "delete from orders where orderid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderid);
		int i = ps.executeUpdate();
		return i > 0;
	}
}
