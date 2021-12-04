package com.apimercadolibre.test.coupon.model;

public class Item {
	
	private String id;
	private Float price;
	public Item() {
	}
	
	public Item(String id, Float price) {
		super();
		this.id = id;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", price=" + price + "]";
	}
	
	

}
