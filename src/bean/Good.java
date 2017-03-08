package bean;

public class Good {
private int goodsId	;
private String goodsName;
private double goodsPrice;
private String goodsDescript;
private int sellsCount;
private int count;
@Override
public String toString() {
	return "商品信息[编号=" + goodsId + ", 名称=" + goodsName
			+ ", 价格=" + goodsPrice + ", 描述=" + goodsDescript
			+ ", 销售量=" + sellsCount + ", 库存量=" + count ;
}
public int getGoodsId() {
	return goodsId;
}
public void setGoodsId(int goodsId) {
	this.goodsId = goodsId;
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
public String getGoodsDescript() {
	return goodsDescript;
}
public void setGoodsDescript(String goodsDescript) {
	this.goodsDescript = goodsDescript;
}
public int getSellsCount() {
	return sellsCount;
}
public void setSellsCount(int sellsCount) {
	this.sellsCount = sellsCount;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}


}
