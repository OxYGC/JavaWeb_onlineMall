package yanggc.domain;

public class CartItem {
	private Product pro;
	private int num;
	private double subTotal;
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubTotal() {
		return pro.getShop_price()*num;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	@Override
	public String toString() {
		return "CartItem [pro=" + pro + ", num=" + num + ", subTotal=" + subTotal + "]";
	}
	
	
}
