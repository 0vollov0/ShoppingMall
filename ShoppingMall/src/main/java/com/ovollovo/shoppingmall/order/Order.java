package com.ovollovo.shoppingmall.order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int code;
	private String userid;
	private String goodscode;
	private String goodscount;
	private int price;
	private String delivery_info;
	private Date registered_time;
	private String formatedTime;
	private String company_code;
	private String invoice_number;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	public String getGoodscount() {
		return goodscount;
	}

	public void setGoodscount(String goodscount) {
		this.goodscount = goodscount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDelivery_info() {
		return delivery_info;
	}

	public void setDelivery_info(String delivery_info) {
		this.delivery_info = delivery_info;
	}

	public void setDelivery_info(DeliveryInfo deliveryInfo) {
		this.delivery_info = deliveryInfo.toString();
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

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	

}
