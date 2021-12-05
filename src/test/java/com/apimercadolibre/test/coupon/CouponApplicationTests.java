package com.apimercadolibre.test.coupon;

import com.apimercadolibre.test.coupon.model.Item;
import com.apimercadolibre.test.coupon.model.ItemRequest;
import com.apimercadolibre.test.coupon.model.ItemResponse;
import com.apimercadolibre.test.coupon.model.NotFoundItems;
import com.apimercadolibre.test.coupon.service.CouponServiceImpl;
import com.apimercadolibre.test.coupon.service.ICouponService;
import com.apimercadolibre.test.coupon.utils.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CouponApplicationTests {


	@Autowired private ICouponService iCouponService;
	@Autowired private CouponServiceImpl couponServiceImpl;




	@Test
	void testCalculateMustReturnAList() {
		Map<String, Float> itemsBody =  new LinkedHashMap<>();
		itemsBody.put("MLA1", Float.valueOf(100));
		itemsBody.put("MLA2", Float.valueOf(210));
		itemsBody.put("MLA3", Float.valueOf(260));
		itemsBody.put("MLA4", Float.valueOf(80));
		itemsBody.put("MLA5", Float.valueOf(90));
		List<String> items =new ArrayList<>(Arrays.asList("MLA1", "MLA2", "MLA4","MLA5"));
		Collections.sort(items);
		List<String> result = couponServiceImpl.calculate(itemsBody,Float.valueOf(500));
		Collections.sort(result);
		assertEquals(items,result);
	}

	@Test
	void testMapToItemListMustReturnAList() {
		Map<String, Float> itemsBody =  new LinkedHashMap<>();
		List<Item> r = new ArrayList<Item>();
		itemsBody.put("MLA1", Float.valueOf(100));
		itemsBody.put("MLA2", Float.valueOf(210));
		r.add(new Item("MLA1", Float.valueOf(100)));
		r.add(new Item("MLA2", Float.valueOf(210)));
		List<Item> result = couponServiceImpl.mapToItemList(itemsBody);
		assertEquals(r,result);
	}


	@Test
	void testGetTotalItemsMustReturnACompleteItemResponse() {
		ItemRequest items = new ItemRequest(Arrays.asList("MCO501668687", "MCO515420765", "MCO500099080","MCO487719207"),Float.valueOf(500000));
		ItemResponse result = new ItemResponse(Arrays.asList("MCO515420765", "MCO500099080"),Float.valueOf(304800));
		assertEquals(result,couponServiceImpl.getTotalItems(items));
	}

	@Test
	void testGetTotalItemsMustReturnAWrongItemResponseNotItem() {
		ItemRequest items = new ItemRequest(Arrays.asList("prueba1", "prueba2", "prueba3","prueba4"),Float.valueOf(500000));
		ItemResponse finalResult = new ItemResponse(new ArrayList<>(), Float.valueOf(0));
		NotFoundItems notFoundItems = new NotFoundItems(Constants.Messages.NOT_ITEM.getMessages(), items.getItemIds());
		finalResult.setNotFoundItems(notFoundItems);
		assertEquals(finalResult,couponServiceImpl.getTotalItems(items));
	}

	@Test
	void testGetTotalItemsMustReturnAWrongItemResponseNotFound() {
		ItemRequest items = new ItemRequest(Arrays.asList("MCO501668687", "MCO515420765", "MCO500099080","prueba4"),Float.valueOf(900000));
		ItemResponse finalResult = new ItemResponse(Arrays.asList("MCO515420765", "MCO501668687", "MCO500099080"), Float.valueOf(554700));
		NotFoundItems notFoundItems = new NotFoundItems(Constants.Messages.NOT_FOUND.getMessages(),Arrays.asList("prueba4"));
		finalResult.setNotFoundItems(notFoundItems);
		assertEquals(finalResult,couponServiceImpl.getTotalItems(items));
	}


	@Test
	void testGetTotalItemsMustReturnAWrongItemResponseMinAmount() {
		ItemRequest items = new ItemRequest(Arrays.asList("MCO501668687", "MCO515420765", "MCO500099080","MCO487719207"),Float.valueOf(1000));
		ItemResponse finalResult = new ItemResponse(new ArrayList<>(), Float.valueOf(0));
		NotFoundItems notFoundItems = new NotFoundItems(Constants.Messages.MIN_AMOUNT.getMessages(), items.getItemIds());
		finalResult.setNotFoundItems(notFoundItems);
		assertEquals(finalResult,couponServiceImpl.getTotalItems(items));
	}





	@Test
	void testgetMeliResponseMustReturnAItem() {
		Item item = new Item("MCO501668687", Float.valueOf(249900));
		Item result= couponServiceImpl.getItemFromMeli("MCO501668687");
		assertEquals(item,result);
	}


	@Test
	void testCreateErroResponseMustChangeResult() {
		ItemResponse finalResult = new ItemResponse(new ArrayList<>(), Float.valueOf(0));
		NotFoundItems notFoundItems = new NotFoundItems("Esto es una prueba", new ArrayList<String>());
		finalResult.setNotFoundItems(notFoundItems);
		assertEquals(finalResult,couponServiceImpl.createErrorResponse("Esto es una prueba",new ArrayList<String>()));
	}


}
