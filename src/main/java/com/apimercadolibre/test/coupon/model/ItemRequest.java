package com.apimercadolibre.test.coupon.model;

import java.util.List;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemRequest {


	@NotEmpty(message = "no puede estar vacio")
	private List<String> itemIds;


	@NotNull(message = "no puede estar vacio")
	private Float amount;

	public ItemRequest() {
		super();
	}

	public ItemRequest(List<String> itemIds, Float amount) {
		super();
		this.itemIds = itemIds;
		this.amount = amount;
	}
	
	public List<String> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amourt) {
		this.amount = amourt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((itemIds == null) ? 0 : itemIds.hashCode());
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
		ItemRequest other = (ItemRequest) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (itemIds == null) {
			if (other.itemIds != null)
				return false;
		} else if (!itemIds.equals(other.itemIds))
			return false;
		return true;
	}
	

}
