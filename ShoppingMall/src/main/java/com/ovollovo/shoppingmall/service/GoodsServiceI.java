package com.ovollovo.shoppingmall.service;

import com.google.gson.JsonArray;
import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsServiceI {
	public Goods getGoods(String code);

	public String getCategoryName(int category);
	
	public int getCategoryCode(String category);
	
	public JsonArray getCategory();

	public Goods[] getCategoryFirstGoods(int category, int n);

	public Goods[] getCategorySecondGoods(int category, int n);
	
	public Goods[] getGoodsList(int category,int page);

	public int getCategoryFirstCount(int category);

	public int getCategorySecondCount(int category);

	public int getCategoryNumber(String code);
	
	public Goods[] getGoodsSearchResult(String name,int page);
	
	public void pushSaleCount(String code,int sale_count);
	
	public Goods[] getBestGoods(int count);
	
	public Goods[] getNewGoods(int count);
	
	public void decreaseStock(String code,int count);
	
	public int getCurrentMaxPage(int category,int page);
	
	public int getCurrentMaxPage(String name,int page);
}
