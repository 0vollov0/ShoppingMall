package com.ovollovo.shoppingmall.member;

import com.ovollovo.shoppingmall.goods.Goods;

public class ShoppingBasket {
	private int count;
	private Goods goods;
	
	public ShoppingBasket(Goods goods) {
		count = 1;
		this.goods = goods;
	}
	
	public ShoppingBasket(int count, Goods goods) {
		super();
		this.count = count;
		this.goods = goods;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public void upCount() {
		count++;
	}
	public void downCount() {
		count--;
	}
}
