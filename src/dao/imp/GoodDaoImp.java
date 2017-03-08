package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import bean.Good;
import dao.GoodDao;

public class GoodDaoImp implements GoodDao {

	public Good good;

	@Override
	public Good query(String goodsname, Connection conn) throws SQLException {

		String sql = "select * from good where goodsname = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, goodsname);
		ResultSet executeQuery = ps.executeQuery();
		while (executeQuery.next()) {
			good = new Good();
			good.setGoodsName(executeQuery.getNString("goodsname"));
			good.setGoodsId(executeQuery.getInt("goodsid"));
			good.setGoodsPrice(executeQuery.getDouble("goodsprice"));
			good.setCount(executeQuery.getInt("count"));
			good.setSellsCount(executeQuery.getInt("SellsCount"));
			return good;
		}
		return null;
	}

	// 减少库存，true表示可以购买
	@Override
	public boolean updateCount(int goodsid, int count, Connection conn)
			throws SQLException {
		String sql = "update good set count = count -? ,sellscount = sellscount + ? where goodsid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, count);
		ps.setInt(2, count);
		ps.setInt(3, goodsid);
		int i = ps.executeUpdate();
		return i > 0;

	}

	@Override
	public List<Good> like(String goodsname, Connection conn)
			throws SQLException {
		List<Good> l = null;
		String sql = "select * from good where goodsname like ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + goodsname + "%");
		ResultSet executeQuery = ps.executeQuery();
		if (executeQuery.next()) {
			l = new ArrayList<Good>();
			while (executeQuery.next()) {
				good = new Good();
				good.setGoodsName(executeQuery.getNString("goodsname"));
				good.setGoodsId(executeQuery.getInt("goodsid"));
				good.setGoodsPrice(executeQuery.getDouble("goodsprice"));
				good.setCount(executeQuery.getInt("count"));
				good.setSellsCount(executeQuery.getInt("SellsCount"));
				l.add(good);
			}

		}

		return l;

	}

	@Override
	public boolean insert(String goodsname, double goodsprice, int count,
			Connection conn) throws SQLException {
		String sql = "insert into good (goodsname,goodsprice,count) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, goodsname);
		ps.setDouble(2, goodsprice);
		ps.setInt(3, count);
		int i = ps.executeUpdate();
		return i > 0;

	}

	@Override
	public boolean set(String goodsname, double goodsprice, int count,
			Connection conn) throws SQLException {
		String sql = "update good set goodsprice = ?,count = ? where goodsname = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, goodsprice);
		ps.setInt(2, count);
		ps.setString(3, goodsname);
		int i = ps.executeUpdate();
		return i > 0;
	}

	@Override
	public List<Good> orderbyCount(Connection conn) throws SQLException {
		String sql = "select * from good order by count";
		ResultSet executeQuery = conn.createStatement().executeQuery(sql);
		List<Good> l = new ArrayList<Good>();
		while (executeQuery.next()) {
			good = new Good();
			good.setGoodsName(executeQuery.getNString("goodsname"));
			good.setGoodsId(executeQuery.getInt("goodsid"));
			good.setGoodsPrice(executeQuery.getDouble("goodsprice"));
			good.setCount(executeQuery.getInt("count"));
			good.setSellsCount(executeQuery.getInt("SellsCount"));
			l.add(good);

		}
		return l;

	}

	@Override
	public List<Good> orderbyPrice(Connection conn) throws SQLException {
		String sql = "select * from good order  by goodsprice";
		ResultSet executeQuery = conn.createStatement().executeQuery(sql);
		List<Good> l = new ArrayList<Good>();
		while (executeQuery.next()) {
			good = new Good();
			good.setGoodsName(executeQuery.getNString("goodsname"));
			good.setGoodsId(executeQuery.getInt("goodsid"));
			good.setGoodsPrice(executeQuery.getDouble("goodsprice"));
			good.setCount(executeQuery.getInt("count"));
			good.setSellsCount(executeQuery.getInt("SellsCount"));
			l.add(good);
		}
		return l;
	}

}
