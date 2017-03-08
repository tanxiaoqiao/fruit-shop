package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Good;

public interface GoodDao {
	
	// ��ѯ��Ʒ��Ϣ
	Good query(String goodsname, Connection conn) throws SQLException;

	// ������Ʒ���
	boolean updateCount(int goodsid, int count, Connection conn)
			throws SQLException;

	// ģ����ѯ
	List<Good> like(String goodsname, Connection conn) throws SQLException;

	// ����Ա����ˮ��
	boolean insert(String goodsname, double goodsprice, int count,
			Connection conn) throws SQLException;
	//�޸�ԭ�ж���
	boolean set(String goodsname, double goodsprice, int count, Connection conn)
			throws SQLException;
//����
	List<Good> orderbyCount(Connection conn) throws SQLException;

	List<Good> orderbyPrice(Connection conn)throws SQLException;


}
