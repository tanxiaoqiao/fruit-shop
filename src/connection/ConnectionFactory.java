package connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private static ConnectionFactory conn = new ConnectionFactory();
	private static ComboPooledDataSource comboPooledDataSource;
	
	static {
		comboPooledDataSource = new ComboPooledDataSource("myconfig");
	}
	
	

	private ConnectionFactory() {

	}

	public static ConnectionFactory getInstance() {
		return conn;
	}
	
	public Connection makeConn() throws SQLException {
		return comboPooledDataSource.getConnection();
	}
}
