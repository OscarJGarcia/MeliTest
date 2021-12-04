package com.apimercadolibre.test.coupon.service;

import java.util.List;
import java.util.Map;

import com.apimercadolibre.test.coupon.model.Item;
import com.apimercadolibre.test.coupon.model.ItemRequest;
import com.apimercadolibre.test.coupon.model.ItemResponse;

public interface ICouponService {
	public ItemResponse getTotalItems(ItemRequest items);
	
	public Item getItemFromMeli(String id);
	
	public List<String> calculate(Map<String, Float> items, Float amount);
	
}
