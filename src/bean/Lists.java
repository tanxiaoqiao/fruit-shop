package bean;

public class Lists {
	private int	orderid	;	
	private int goodsid	;	
	private int	count	;
	private double totalMoney;
	private String goodsName;
	private double goodsPrice;
	
	
	
	
	
	@Override
	public String toString() {
		return "Lists [orderid=" + orderid + ", goodsid=" + goodsid
				+ ", count=" + count + ", totalMoney=" + totalMoney
				+ ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ "]\n";
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


}