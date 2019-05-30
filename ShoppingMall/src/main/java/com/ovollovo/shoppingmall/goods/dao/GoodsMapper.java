package com.ovollovo.shoppingmall.goods.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.goods.Category;
import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsMapper {
	public void registerGoods(@Param("name") String name, @Param("code") String code, @Param("category") int category,
			@Param("price") int price, @Param("stock") int stock, @Param("description") String description,
			@Param("thumbnail_image") String thumbnail_image);

	public Goods searchGoods(@Param("code") String code);

	public String searchCode(@Param("code") String code);

	public Goods[] searchNewGoods(@Param("count") int count);

	public String searchCategoryName(@Param("category") int category);
	
	public int searchCategoryCode(@Param("name") String category);
	
	public Category[] searchCategory();

	public Goods[] searchCategoryFirstGoods(@Param("category") int category, @Param("n") int n, @Param("m") int m);

	public Goods[] searchCategorySecondGoods(@Param("category") int category, @Param("n") int n, @Param("m") int m);

	public int searchCategoryFirstCount(@Param("category") int category);

	public int searchCategorySecondCount(@Param("category") int category);

	public int searchCategoryNumber(@Param("code") String code);

	public Goods[] searchGoodsByName(@Param("name") String name, @Param("n") int n, @Param("m") int m);
	
	public int searchGoodsByNameCount(@Param("name") String name);

	public void stackSaleCount(@Param("code") String code, @Param("sale_count") int sale_count);

	public Goods[] searchBestGoods(@Param("count") int count);

	public void modifyGoods(@Param("name") String name, @Param("code") String code, @Param("category") int category,
			@Param("price") int price, @Param("stock") int stock, @Param("description") String description,@Param("wherecode") String wherecode);
	public void modifyGoodsImage(@Param("code") String code,@Param("thumbnail_image") String thumbnail_image);
	
	public void deleteGoods(@Param("code") String code);
	
	public void decreaseStock(@Param("code") String code , @Param("count") int count);

}
