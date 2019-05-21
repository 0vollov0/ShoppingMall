package com.ovollovo.shoppingmall.goods;

import java.util.Date;

public class Goods {	
	private String name;
	private String code;
	private int classification_1;
	private int classification_2;
	private int price;
	private int stock;
	private String description;
	private String thumbnail_image;
	private Date registered_time;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getClassification_1() {
		return classification_1;
	}
	public void setClassification_1(int classification_1) {
		this.classification_1 = classification_1;
	}
	public int getClassification_2() {
		return classification_2;
	}
	public void setClassification_2(int classification_2) {
		this.classification_2 = classification_2;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail_image() {
		return thumbnail_image;
	}
	public void setThumbnail_image(String thumbnail_image) {
		this.thumbnail_image = thumbnail_image;
	}
	public Date getRegistered_time() {
		return registered_time;
	}
	public void setRegistered_time(Date registered_time) {
		this.registered_time = registered_time;
	}
	@Override
	public String toString() {
		return "Goods [name=" + name + ", code=" + code + ", classification_1=" + classification_1
				+ ", classification_2=" + classification_2 + ", price=" + price + ", stock=" + stock + ", description="
				+ description + ", thumbnail_image=" + thumbnail_image + "]";
	}
	
	
}
