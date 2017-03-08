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
	 static Manager instance = new Manager();// ������ϵ �����Ե�����ģʽ����������
	List<Orders> manyOrder;// �����manyOrder���������û�

	private Manager() {

	}

	public static Manager getInstance() {
		return instance;
	}

	// ����true ˵���û����ظ�
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
			mes = userMethod.me;// ��õ�ǰ��¼���˵���Ϣ
			return true;
		}
		return false;
	}

	// ������޸��û�
	public boolean check(String username) throws SQLException {
		conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.queryName(username, conn)) {
			return true;
		}
		return false;

	}

	// ��ѯˮ����Ϣ
	public List<Good> queryGoods(String goodsname) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		List<Good> manygoods = goodMethod.like(goodsname, conn);		
		return manygoods;

	}

	// ����
	public int buyGoods(String goodsname, String username, int count)
			throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		conn.setAutoCommit(false);
		goods = goodMethod.query(goodsname, conn);// �Ȼ��ˮ��
		if (goods == null) {
			return 4;
		}
		double totalmoney = count * goods.getGoodsPrice();
		if (goods.getCount() > count) {// ��ʾ���ok

			if (mes.getMoney() > totalmoney) {// ��ʾ���ok
				Savepoint savepoint = conn.setSavepoint();

				boolean a = userMethod.updateMoney(mes.getUserid(),
						-totalmoney, conn);// ��ʾ�û���������

				boolean b = goodMethod.updateCount(goods.getGoodsId(), count,
						conn);// ���Ҳ�ı���

				boolean c = orderMethod.insert(mes.getUserid(), totalmoney,
						conn);// ��������
				orders = orderMethod.order;// ���order

				boolean d = listMethod.insert(orders.getOrderid(),
						goods.getGoodsId(), count, conn);
				if (a & b & c & d) {
					conn.commit();
					return 0;

				} else {
					conn.rollback(savepoint);
					return 3;// ��Ϊ����û��ͬ��������ʧ�ܣ�����3
				}
			} else {
				return 2;// ��ʾ����
			}
		} else {
			return 1;// ��ʾ��治��
		}

	}

	// ��ֵ
	public boolean doSaveMoney(double money) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		if (userMethod.updateMoney(mes.getUserid(), money, conn)) {
			return true;
		}
		return false;

	}

	// �鿴�Լ��Ķ���
	public List<Orders> doViewOrder() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		manyOrder = orderMethod.query(mes.getUserid(), conn);
		return manyOrder;

	}

	/**
	 * ɾ������ 1 ɾ��list 2 ɾ��orders 3 ����good��count sellscount 4���� user��money
	 * 
	 * @param orderid
	 * @return
	 * @throws SQLException
	 */
	public int doDeleteOrder(int orderid) throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();
		// �ж��Ƿ��иö���
		conn.setAutoCommit(false);
		Savepoint savepoint = conn.setSavepoint();
		if (orderMethod.queryAll(orderid, conn)) {// ���order��Ϣ,��ʾ��������
			orders = orderMethod.order;
			listMethod.query(orderid, conn);// ���list��Ϣ
			lists = listMethod.list;
			double money = orders.getTotalmoney();
			boolean a = listMethod.delete(orderid, conn); // 1 ɾ��list
			boolean b = orderMethod.delete(orderid, conn);// 2 ɾ��orders
			boolean c = userMethod.updateMoney(orders.getUserid(), money, conn);// 4����
			boolean d = goodMethod.updateCount(lists.getGoodsid(),
					-lists.getCount(), conn);// 3 ����good��count
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
			return 1;// ��ʾ�������
		}
		if (goodMethod.insert(goodsname, goodsprice, count, conn)) {
			return 2;
		}
		return 3;// ʧ��
	}

	public List<Lists> docheckOrder() throws SQLException {
		Connection conn = ConnectionFactory.getInstance().makeConn();		
		 return orderMethod.queryO(conn);		

	}

	// ����Ա�޸�ˮ����Ϣ
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