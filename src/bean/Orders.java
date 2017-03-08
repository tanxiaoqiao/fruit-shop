package bean;

import java.sql.Timestamp;



public class Orders {
private int orderid		;
private int userid	;	
private double totalmoney	;
private Timestamp	orderdate	;
private String description ;




public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "Order [orderid=" + orderid + ", userid=" + userid + ", totalmoney="
			+ totalmoney + ", orderdate=" + orderdate + description + "]";
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public double getTotalmoney() {
	return totalmoney;
}
public void setTotalmoney(double totalmoney) {
	this.totalmoney = totalmoney;
}
public Timestamp getOrderdate() {
	return orderdate;
}
public void setOrderdate(Timestamp orderdate) {
	this.orderdate = orderdate;
}


}
