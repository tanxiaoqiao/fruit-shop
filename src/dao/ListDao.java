package dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface ListDao {
//�����������������������

	
	//����list
	public boolean insert(int orderid,int  goodsid,int count,Connection conn)throws SQLException;
//ɾ��list
	public boolean delete(int orderid, Connection conn)throws SQLException;
//���ݶ����Ų�ѯlist
	boolean query(int orderid, Connection conn) throws SQLException;
}
