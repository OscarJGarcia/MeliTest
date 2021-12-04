package com.apimercadolibre.test.coupon.model;

import java.util.List;

public class ItemResponse{
	
	private List<String> itemIds;
	private Float total;
	private NotFoundItems notFoundItems;
	
	
	public ItemResponse(List<String> itemIds, Float total) {
		super();
		this.itemIds = itemIds;
		this.total = total;
	}
	
	public List<String> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}

	public NotFoundItems getNotFoundItems() {
		return notFoundItems;
	}

	public void setNotFoundItems(NotFoundItems notFoundItems) {
		this.notFoundItems = notFoundItems;
	}

	@Override
	public String toString() {
		return "ItemResponse{" +
				"itemIds=" + itemIds +
				", total=" + total +
				", notFoundItems=" + notFoundItems +
				'}';
	}
}
