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
	public Goods getGoods(String code) {
		return goodsMapper.searchGoods(code);
	}
	
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

	@Override
	public int getCategoryNumber(String code) {
		return goodsMapper.searchCategoryNumber(code);
	}
}
