package yanggc.domain;

public class OrderItem {

	/*`itemid` varchar(32) NOT NULL,
	  `count` int(11) DEFAULT NULL,
	  `subtotal` double DEFAULT NULL,
	  `pid` varchar(32) DEFAULT NULL,
	  `oid` varchar(32) DEFAULT NULL*/
	
	private String itemid;//订单项主键
	private Integer count;//商品数量
	private Double subtotal;//订单项小计
	private Product product;//该订单项内部是哪个商品
	private Order order;//该订单项属于哪个订单
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
}
