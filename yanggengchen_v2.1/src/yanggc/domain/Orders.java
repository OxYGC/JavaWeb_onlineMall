package yanggc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
	/*
	 * oid` varchar(32) NOT NULL, `ordertime` datetime DEFAULT NULL, `total` double
	 * DEFAULT NULL, `state` int(11) DEFAULT NULL, `address` varchar(30) DEFAULT
	 * NULL, `name` varchar(20) DEFAULT NULL, `telephone` varchar(20) DEFAULT NULL,
	 * `uid` varchar(32) DEFAULT NULL
	 */
	private String oid;
	private Date ordertime;
	private double total;
	private int state;
	private String address;
	private String name;
	private String telephone;
	/* private String uid; */
	private User user;
	private List<OrderItem> list = new ArrayList<OrderItem>();

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
