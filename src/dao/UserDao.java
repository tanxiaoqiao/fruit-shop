package dao;

import java.sql.Connection;
import java.sql.SQLException;



public interface UserDao {
	/**
	 * �û���һЩ�������� �� 1ע���û� insert
	 * 
	 * @param
	 */

	

	public boolean insert(String name, String password, Connection conn)
			throws SQLException;
	//��ѯ�û��Ƿ����
	public boolean queryName(String username, Connection conn) throws SQLException;
//��¼
	public boolean queryAll(String username, String password, Connection conn)
			throws SQLException;
//����ʱ���鿴���
	public boolean updateMoney(int userid, double total, Connection conn) throws SQLException ;
	
	
}
