package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import bean.Good;
import bean.Lists;
import bean.Orders;
import bean.User;
import connection.ConnectionFactory;
import dao.imp.GoodDaoImp;
import dao.imp.ListDaoImp;
import dao.imp.OrderDaoImp;
import dao.imp.UserDaoImp;

public class Manager {

	Connection conn;
	User mes;
	Good goods;
	Orders orders;
	Lists lists;
	static UserDaoImp userMethod = new UserDaoImp();
	static OrderDaoImp orderMethod = new OrderDaoImp();
	static GoodDaoImp goodMethod = new GoodDaoImp();
	static ListDaoImp listMethod = new ListDaoImp();
	 static Manager instance = new Manager();// 依赖关系 ，所以单例子模式？？？？？
	List<Orders> manyOrder;// 这里的manyOrder属于所有用户

	private Manager() {

	}

	public static Manager getInstance() {
		return instance;
	}

	// 返回true 说明用户名重复
	public boolean doReg(String username, String password) throws SQLException {
		conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.insert(username, password, conn)) {
			return true;
		}
		return false;
	}

	public boolean doLog(String username, String password) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.queryAll(username, password, conn)) {
			mes = userMethod.me;// 获得当前登录的人的信息
			return true;
		}
		return false;
	}

	// 检查有无该用户
	public boolean check(String username) throws SQLException {
		conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.queryName(username, conn)) {
			return true;
		}
		return false;

	}

	// 查询水果信息
	public List<Good> queryGoods(String goodsname) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		List<Good> manygoods = goodMethod.like(goodsname, conn);		
		return manygoods;

	}

	// 买东西
	public int buyGoods(String goodsname, String username, int count)
			throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		conn.setAutoCommit(false);
		goods = goodMethod.query(goodsname, conn);// 先获得水果
		if (goods == null) {
			return 4;
		}
		double totalmoney = count * goods.getGoodsPrice();
		if (goods.getCount() > count) {// 表示库存ok

			if (mes.getMoney() > totalmoney) {// 表示余额ok
				Savepoint savepoint = conn.setSavepoint();

				boolean a = userMethod.updateMoney(mes.getUserid(),
						-totalmoney, conn);// 表示用户余额减少了

				boolean b = goodMethod.updateCount(goods.getGoodsId(), count,
						conn);// 库存也改变了

				boolean c = orderMethod.insert(mes.getUserid(), totalmoney,
						conn);// 订单增加
				orders = orderMethod.order;// 获得order

				boolean d = listMethod.insert(orders.getOrderid(),
						goods.getGoodsId(), count, conn);
				if (a & b & c & d) {
					conn.commit();
					return 0;

				} else {
					conn.rollback(savepoint);
					return 3;// 因为事物没有同步，所以失败，返回3
				}
			} else {
				return 2;// 表示余额不够
			}
		} else {
			return 1;// 表示库存不够
		}

	}

	// 充值
	public boolean doSaveMoney(double money) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.updateMoney(mes.getUserid(), money, conn)) {
			return true;
		}
		return false;

	}

	// 查看自己的订单
	public List<Orders> doViewOrder() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		manyOrder = orderMethod.query(mes.getUserid(), conn);
		return manyOrder;

	}

	/**
	 * 删除订单 1 删除list 2 删除orders 3 撤销good的count sellscount 4撤销 user的money
	 * 
	 * @param orderid
	 * @return
	 * @throws SQLException
	 */
	public int doDeleteOrder(int orderid) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		// 判断是否有该订单
		conn.setAutoCommit(false);
		Savepoint savepoint = conn.setSavepoint();
		if (orderMethod.queryAll(orderid, conn)) {// 获得order信息,表示订单存在
			orders = orderMethod.order;
			listMethod.query(orderid, conn);// 获得list信息
			lists = listMethod.list;
			double money = orders.getTotalmoney();
			boolean a = listMethod.delete(orderid, conn); // 1 删除list
			boolean b = orderMethod.delete(orderid, conn);// 2 删除orders
			boolean c = userMethod.updateMoney(orders.getUserid(), money, conn);// 4撤销
			boolean d = goodMethod.updateCount(lists.getGoodsid(),
					-lists.getCount(), conn);// 3 撤销good的count
			if (a & b & c & d) {
				System.out.println("ok");
				conn.commit();
				return 0;
			}
			else {
				conn.rollback(savepoint);
				return 1;
		} 
		}
		return 2;
	}

	public int doAdd(String goodsname, double goodsprice, int count)
			throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		if (goodMethod.query(goodsname, conn) != null) {
			return 1;// 表示添加重名
		}
		if (goodMethod.insert(goodsname, goodsprice, count, conn)) {
			return 2;
		}
		return 3;// 失败
	}

	public List<Lists> docheckOrder() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();		
		 return orderMethod.queryO(conn);		

	}

	// 管理员修改水果信息
	public boolean doChangeFruit(String goodsname, double goodsprice, int count)
			throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		if (goodMethod.query(goodsname, conn) != null) {
			if (goodMethod.set(goodsname, goodsprice, count, conn)) {
				return true;
			}
		}
		return false;
	}

	public List<Good> doSort(int i) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		List<Good> manyGoods = null;
		if (i == 1) {
			manyGoods = goodMethod.orderbyCount(conn);
		}
		if (i == 2) {
			manyGoods = goodMethod.orderbyPrice(conn);
		}
		return manyGoods;
	}

}