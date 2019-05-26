package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Category;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;

@Service
public class GoodsService implements GoodsServiceI {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsJson goodsJson;
	

	@Override
	public Goods getGoods(String code) {
		return goodsMapper.searchGoods(code);
	}

	@Override
	public String getCategoryName(int category) {
		return goodsMapper.searchCategoryName(category);
	}
	
	@Override
	public int getCategoryCode(String category) {
		return goodsMapper.searchCategoryCode(category);
	}
	
	@Override
	public JsonArray getCategory() {
		return goodsJson.getCategoryJson( goodsMapper.searchCategory());
	}
	
	@Override
	public Goods[] getCategoryFirstGoods(int category, int n) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategoryFirstGoods(category / 100, 4 * (n - 1), 4 * n);
	}

	@Override
	public Goods[] getCategorySecondGoods(int category, int n) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategorySecondGoods(category, 4 * (n - 1), 4 * n);
	}

	@Override
	public int getCategoryFirstCount(int category) {
		// TODO Auto-generated method stub
		return goodsMapper.searchCategoryFirstCount(category / 100);
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

	@Override
	public Goods[] getGoodsSearchResult(String name) {
		return goodsMapper.searchGoodsByName(name);
	}

	@Override
	public void pushSaleCount(String code, int sale_count) {
		goodsMapper.stackSaleCount(code, sale_count);

	}

	@Override
	public Goods[] getBestGoods(int count) {
		return goodsMapper.searchBestGoods(count);
	}

	@Override
	public Goods[] getNewGoods(int count) {
		return goodsMapper.searchNewGoods(count);
	}

	@Override
	public JsonObject modifyGoods(Goods goods, String wherecode) {
		if (goodsMapper.searchGoods(wherecode) == null) {
			return goodsJson.getModifyGoodsResultJson(1,goods.getCode());	
		}
		if (!goods.getCode().equals(wherecode)) {
			if (goodsMapper.searchGoods(goods.getCode()) != null) {
				return goodsJson.getModifyGoodsResultJson(2,goods.getCode());
			}
		}
		goodsMapper.modifyGoods(goods.getName(), goods.getCode(), goods.getCategory(), goods.getPrice(), goods.getStock(), goods.getDescription(), wherecode);
		return goodsJson.getModifyGoodsResultJson(0,goods.getCode());
	}

	@Override
	public int modifyGoodsImage(String code, String thumbnail_image) {
		if (goodsMapper.searchGoods(code) == null) {
			return 1;
		}
		goodsMapper.modifyGoodsImage(code, thumbnail_image);
		return 0;
	}

	@Override
	public void deleteGoods(String code) {
		goodsMapper.deleteGoods(code);
	}

	


}
