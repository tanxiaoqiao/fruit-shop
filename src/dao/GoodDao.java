package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Good;

public interface GoodDao {
	
	// 查询商品信息
	Good query(String goodsname, Connection conn) throws SQLException;

	// 更新商品库存
	boolean updateCount(int goodsid, int count, Connection conn)
			throws SQLException;

	// 模糊查询
	List<Good> like(String goodsname, Connection conn) throws SQLException;

	// 管理员新增水果
	boolean insert(String goodsname, double goodsprice, int count,
			Connection conn) throws SQLException;
	//修改原有订单
	boolean set(String goodsname, double goodsprice, int count, Connection conn)
			throws SQLException;
//排序
	List<Good> orderbyCount(Connection conn) throws SQLException;

	List<Good> orderbyPrice(Connection conn)throws SQLException;


}
