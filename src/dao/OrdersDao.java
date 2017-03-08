package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Lists;
import bean.Orders;


public interface OrdersDao {
	
	//���붩��
	boolean insert(int userid, double totalmoney, Connection conn)
			throws SQLException;
	//��ѯ���ж���
	List<Orders> query(int userid, Connection conn) throws SQLException;
	//ɾ������
	boolean delete(int orderid, Connection conn) throws SQLException;
	//���ݶ�����ѯ������Ϣ
	boolean queryAll(int orderid, Connection conn) throws SQLException;
	List<Lists> queryO( Connection conn) throws SQLException;

}
