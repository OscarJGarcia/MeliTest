package com.apimercadolibre.test.coupon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemRequest {


	@NotEmpty(message = "no puede estar vacio")
	@JsonProperty("item_ids")
	private List<String> itemIds;


	@NotNull(message = "no puede estar vacio")
	private Float amount;
	
	
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
	

}
