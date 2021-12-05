package com.apimercadolibre.test.coupon.model;

import java.util.List;

public class ItemResponse{
	
	private List<String> itemIds;
	private Float total;
	private NotFoundItems notFoundItems;

	public ItemResponse() {
		super();
	}


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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemIds == null) ? 0 : itemIds.hashCode());
		result = prime * result + ((notFoundItems == null) ? 0 : notFoundItems.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemResponse other = (ItemResponse) obj;
		if (itemIds == null) {
			if (other.itemIds != null)
				return false;
		} else if (!itemIds.equals(other.itemIds))
			return false;
		if (notFoundItems == null) {
			if (other.notFoundItems != null)
				return false;
		} else if (!notFoundItems.equals(other.notFoundItems))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
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
