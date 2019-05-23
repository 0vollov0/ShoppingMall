package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsServiceI {
	public String getCategoryName(int category);

	public Goods[] getCategoryFirstGoods(int category, int n);

	public Goods[] getCategorySecondGoods(int category, int n);

	public int getCategoryFirstCount(int category);

	public int getCategorySecondCount(int category);
}
