package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import bean.User;


import dao.UserDao;

public class UserDaoImp implements UserDao {
	public User me;

	// ע�᷽����insert,����true���Ǹ��û�����

	@Override
	public boolean insert(String username, String password, Connection conn)
			throws SQLException {
		if (queryName(username, conn)) {
			return false;
		} else {
			String sql = "insert into user (username , password) values (?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			int count = ps.executeUpdate();
			queryName(username, conn);
			return count > 0;
		}

	}

	// �鿴�û����ķ���,����true���Ǵ���
	@Override
	public boolean queryName(String username, Connection conn) throws SQLException {		
		
			String sql = "select * from user where username = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet executeQuery = ps.executeQuery();
			if(executeQuery .next()){
			User user= new User();
			user.setUserid(executeQuery.getInt("userid"));
			user.setUsername(executeQuery.getString("username"));
			user.setPassword(executeQuery.getString("password"));
			user.setMoney(executeQuery.getDouble("money"));	
			me=user;
			return true;
			}
		
		return false;
	}

	@Override//��¼���� ��true ��ʾ����
	public boolean queryAll(String username, String password, Connection conn)
			throws SQLException {
		queryName( username, conn);
		String sql= "select*from user where username = ? and password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet executeQuery = ps.executeQuery();
		if(executeQuery.next()){	
			
			return true;
		}
		return false;
	}

	

	@Override
	//�����޸ģ���ֵ�͹��򶼿���ʹ�ô˷���
	public boolean updateMoney(int userid, double total, Connection conn) throws SQLException {
	String sql="update user set money = money + ? where userid =?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setDouble(1, total);
	ps.setInt(2, userid);
	int i = ps.executeUpdate();	
	return i>0;			

	
	}
}
