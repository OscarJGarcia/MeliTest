package com.apimercadolibre.test.coupon;

import com.apimercadolibre.test.coupon.model.Item;
import com.apimercadolibre.test.coupon.model.ItemRequest;
import com.apimercadolibre.test.coupon.model.ItemResponse;
import com.apimercadolibre.test.coupon.service.CouponServiceImpl;
import com.apimercadolibre.test.coupon.service.ICouponService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CouponApplicationTests {

	@Mock
	ICouponService iCouponService;

	@InjectMocks
	CouponServiceImpl couponServiceImpl;


	@Test
	void testCalculateMustReturnAList() {
		Map<String, Float> itemsBody =  new LinkedHashMap<>();
		itemsBody.put("MLA1", Float.valueOf(100));
		itemsBody.put("MLA2", Float.valueOf(210));
		itemsBody.put("MLA3", Float.valueOf(260));
		itemsBody.put("MLA4", Float.valueOf(80));
		itemsBody.put("MLA5", Float.valueOf(90));
		List<String> items =new ArrayList<>(Arrays.asList("MLA1", "MLA2", "MLA4","MLA5"));
		CouponServiceImpl couponService = new CouponServiceImpl();
		Collections.sort(items);
		List<String> result = couponService.calculate(itemsBody,Float.valueOf(500));
		Collections.sort(result);
		assertEquals(items,result);
	}

	@Test
	void testMapToItemListMustReturnAList() {
		Map<String, Float> itemsBody =  new LinkedHashMap<>();
		List<Item> r = new ArrayList<Item>();
		itemsBody.put("MLA1", Float.valueOf(100));
		itemsBody.put("MLA2", Float.valueOf(210));
		r.add(new Item("MLA1",Float.valueOf(100)));
		r.add(new Item("MLA2",Float.valueOf(210)));
		CouponServiceImpl couponService = new CouponServiceImpl();
		List<Item> result = couponService.mapToItemList(itemsBody);
		assertEquals(r,result);
	}


	@Test
	void testGetMaMustReturnAList() {
		ItemRequest items = new ItemRequest(Arrays.asList("MCO501668687", "MCO515420765", "MCO500099080","MCO487719207"),Float.valueOf(500000));
		ItemResponse result = new ItemResponse(Arrays.asList("MCO515420765", "MCO500099080"),Float.valueOf(304800));
		CouponServiceImpl couponService = new CouponServiceImpl();
		assertEquals(result,couponService.getTotalItems(items));
	}


}
