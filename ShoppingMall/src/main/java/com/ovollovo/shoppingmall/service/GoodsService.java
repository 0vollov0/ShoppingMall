package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;

@Service
public class GoodsService implements GoodsServiceI {
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public String getCategoryName(int category) {
		return goodsMapper.searchCategoryName(category);
	}

	@Override
	public Goods[] getCategoryFirstGoods(int category, int n) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategoryFirstGoods(category/100, 4*(n-1), 4*n);
	}

	@Override
	public Goods[] getCategorySecondGoods(int category, int n) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategorySecondGoods(category, 4*(n-1), 4*n);
	}

	@Override
	public int getCategoryFirstCount(int category) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategoryFirstCount(category/100);
	}

	@Override
	public int getCategorySecondCount(int category) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategorySecondCount(category);
	}

	/*
	@Override
	public String getCategory_1Name(String code) {
		return goodsMapper.searchCategory_1Names(Integer.parseInt(code));
	}

	@Override
	public String getCategory_2Name(String code) {
		return goodsMapper.searchCategory_2Names(Integer.parseInt(code));
	}

	@Override
	public Goods[] getClassification_1Goods(String classification_1, int n) {
		return goodsMapper.searchClassification_1Goods(Integer.parseInt(classification_1), 4*(n-1), 4*n);
	}
	
	@Override
	public Goods[] getClassification_2Goods(String classification_2, int n) {
		return goodsMapper.searchClassification_2Goods(Integer.parseInt(classification_2), 4*(n-1), 4*n);
	}
	
	@Override
	public int getClassification_1Count(String classification_1) {
		return goodsMapper.searchClassification_1Count(Integer.parseInt(classification_1));
	}

	@Override
	public int getClassification_2Count(String classification_2) {
		return goodsMapper.searchClassification_2Count(Integer.parseInt(classification_2));
	}*/


}
