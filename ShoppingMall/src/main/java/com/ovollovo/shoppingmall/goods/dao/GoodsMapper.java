package com.ovollovo.shoppingmall.goods.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.goods.Goods;

public interface GoodsMapper {
	public void registerGoods(@Param("name") String name, @Param("code") String code,
			@Param("classification_1") int classification_1, @Param("classification_2") int classification_2,
			@Param("price") int price, @Param("stock") int stock, @Param("description") String description,
			@Param("thumbnail_image") String thumbnail_image);

	public Goods searchGoods(@Param("code") String code);

	public String searchCode(@Param("code") String code);

	public Goods[] searchNewGoods();

	public String searchCategory_1Names(@Param("code") int code);

	public String searchCategory_2Names(@Param("code") int code);

	public Goods[] searchClassification_1Goods(@Param("classification_1") int classification_1, @Param("n") int n,@Param("m") int m);
	
	public Goods[] searchClassification_2Goods(@Param("classification_2") int classification_2, @Param("n") int n,@Param("m") int m);
	
	public int searchClassification_1Count(@Param("classification_1") int classification_1);
	
	public int searchClassification_2Count(@Param("classification_2") int classification_2);
}
