package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
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
		return goodsJson.getCategoryJson(goodsMapper.searchCategory());
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
	public Goods[] getGoodsList(int category, int page) {
		int LastPage= getGoodsListLastPage(category);
		if (page > LastPage ) {
			if (category%100 == 0) {
				return goodsMapper.searchCategoryFirstGoods(category / 100, 4 * (LastPage - 1), 4);
			}
			return goodsMapper.searchCategorySecondGoods(category, 4 * (LastPage - 1),4);
		}
		if (category%100 == 0) {
			return goodsMapper.searchCategoryFirstGoods(category / 100, 4 * (page - 1), 4);
		}
		return goodsMapper.searchCategorySecondGoods(category, 4 * (page - 1), 4);
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
	public Goods[] getGoodsSearchResult(String name,int page) {
		int LastPage= getSearchResultLastPage(name);
		if (page > LastPage ) {
			return goodsMapper.searchGoodsByName(name, 4 * (LastPage - 1),4);
		}
		return goodsMapper.searchGoodsByName(name, 4*(page-1),4);
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
	public void decreaseStock(String code, int count) {
		goodsMapper.decreaseStock(code, count);

	}
	
	public int getGoodsListLastPage(int category) {
		int lastPage;
		int goodsCount;
		if (category % 100 == 0) {
			goodsCount = goodsMapper.searchCategoryFirstCount(category / 100);
		}else {
			goodsCount = goodsMapper.searchCategorySecondCount(category);
		}
		lastPage = goodsCount/4;
		if (goodsCount%4 > 0) {
			lastPage++;
		}
		if (lastPage == 0) {
			lastPage = 1;
		}
		return lastPage;
	}
	
	public  int getSearchResultLastPage(String name) {
		int lastPage;
		int goodsCount = goodsMapper.searchGoodsByNameCount(name);				
		lastPage = goodsCount/4;
		if (goodsCount%4 > 0) {
			lastPage++;
		}
		if (lastPage == 0) {
			lastPage = 1;
		}
		return lastPage;
	}

	@Override
	public int getCurrentMaxPage(int category,int page) {
		int LastPage = getGoodsListLastPage(category);
		if (((page-1)/10+1)*10 > LastPage) {
			return LastPage;
		}
		return ((page-1)/10+1)*10 ;
	}

	@Override
	public int getCurrentMaxPage(String name, int page) {
		int LastPage = getSearchResultLastPage(name);
		if (((page-1)/10+1)*10 > LastPage) {
			return LastPage;
		}
		return ((page-1)/10+1)*10 ;
	}
}
