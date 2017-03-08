package bean;

public class User {
	
private int	userid;
private  String	username;
private  String	password;	
private double	money;


@Override
public String toString() {
	return "User [userid=" + userid + ", username=" + username + ", password="
			+ password + ", money=" + money + "]";
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public double getMoney() {
	return money;
}
public void setMoney(double money) {
	this.money = money;
}		
}
