package com.ovollovo.shoppingmall.goods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Goods {
	private String name;
	private String code;
	private int category;
	private int price;
	private int stock;
	private String description;
	private String thumbnail_image;
	private Date registered_time;
	private String formatedTime;

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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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
		setFormatedTime(this.registered_time);
	}

	public String getFormatedTime() {
		return formatedTime;
	}

	public void setFormatedTime(Date registered_time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy³â MM¿ù ddÀÏ");
		this.formatedTime = dateFormat.format(this.registered_time);
	}

	@Override
	public String toString() {
		return "Goods [name=" + name + ", code=" + code + ", category=" + category + ", price=" + price + ", stock="
				+ stock + ", description=" + description + ", thumbnail_image=" + thumbnail_image + ", registered_time="
				+ registered_time + ", formatedTime=" + formatedTime + "]";
	}
		
}
