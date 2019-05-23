package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsServiceI {
	public String getCategory_1Name(String code);
	
	public String getCategory_2Name(String code);
	
	public Goods[] getClassification_1Goods(String classification_1,int n);
	
	public Goods[] getClassification_2Goods(String classification_2,int n);
	
	public int getClassification_1Count(String classification_1);
	
	public int getClassification_2Count(String classification_2);
}
