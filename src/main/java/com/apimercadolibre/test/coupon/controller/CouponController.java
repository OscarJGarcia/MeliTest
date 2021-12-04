package com.apimercadolibre.test.coupon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.apimercadolibre.test.coupon.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apimercadolibre.test.coupon.model.Item;
import com.apimercadolibre.test.coupon.model.ItemRequest;
import com.apimercadolibre.test.coupon.model.ItemResponse;
import com.apimercadolibre.test.coupon.service.ICouponService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class CouponController {
	
	@Autowired
	private ICouponService iCouponService;
	
	@PostMapping("/coupon")
	public ResponseEntity<?> getTotalItems(@Valid @RequestBody ItemRequest items, BindingResult result) {
			
		Map<String, Object> response = new HashMap<>();
		ItemResponse itemResponse = null;
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			itemResponse = iCouponService.getTotalItems(items);
		} catch(Exception e) {
			response.put("mensaje", Constants.Messages.ERROR_ITEMS.getMessages());
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ItemResponse>(itemResponse, HttpStatus.OK);
	}
	
	@GetMapping("/couponAlgoritm")
	public ResponseEntity<?> calculateItems() {
		
		Map<String, Object> response = new HashMap<>();
		Map<String, Float> itemsBody =  new LinkedHashMap<>();
		itemsBody.put("MLA1", Float.valueOf(100));
		itemsBody.put("MLA2", Float.valueOf(210));
		itemsBody.put("MLA3", Float.valueOf(260));
		itemsBody.put("MLA4", Float.valueOf(80));
		itemsBody.put("MLA5", Float.valueOf(90));
		
		List<String> result =null;
		
		try {
			result = iCouponService.calculate(itemsBody, Float.valueOf(500));
		} catch(Exception e) {
			response.put("mensaje", Constants.Messages.ERROR_ALG.getMessages());
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	}
	

	
	

}
