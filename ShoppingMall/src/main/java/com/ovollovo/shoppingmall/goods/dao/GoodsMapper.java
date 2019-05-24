package com.ovollovo.shoppingmall.goods.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsMapper {
	public void registerGoods(@Param("name") String name, @Param("code") String code, @Param("category") int category,
			@Param("price") int price, @Param("stock") int stock, @Param("description") String description,
			@Param("thumbnail_image") String thumbnail_image);

	public Goods searchGoods(@Param("code") String code);

	public String searchCode(@Param("code") String code);

	public Goods[] searchNewGoods();

	public String searchCategoryName(@Param("category") int category);

	public Goods[] searchCategoryFirstGoods(@Param("category") int category, @Param("n") int n, @Param("m") int m);

	public Goods[] searchCategorySecondGoods(@Param("category") int category, @Param("n") int n, @Param("m") int m);

	public int searchCategoryFirstCount(@Param("category") int category);

	public int searchCategorySecondCount(@Param("category") int category);
	
	public int searchCategoryNumber(@Param("code") String code);
	
	public Goods[] searchGoodsByName(@Param("name") String name);

}
