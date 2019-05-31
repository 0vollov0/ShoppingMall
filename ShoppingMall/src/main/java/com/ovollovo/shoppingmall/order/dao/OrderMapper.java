package com.ovollovo.shoppingmall.order.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.order.Order;

public interface OrderMapper {
	public void registerOrder(@Param("userid") String userid, @Param("goodscode") String goodscode,
			@Param("goodscount") String goodscount,@Param("goodsname") String goodsname, @Param("price") int price,@Param("delivery_info") String delivery_info);

	public Order[] searchOrderList(@Param("n") int n,@Param("m") int m);

	public Order[] searchOrderListById(@Param("userid") String userid,@Param("n") int n,@Param("m") int m);
	
	public void registerShippingInfo(@Param("code") int code,@Param("companyCode") String companyCode,@Param("invoiceNumber") String invoiceNumber);
	
	public int getOrderListCount();
	
	public int getOrderListCountById(@Param("userid")String userid);
	
	public String searchDeliveryInfo(@Param("code") int code);
	
	public void deleteOrderList(@Param("userid")String userid);

}
