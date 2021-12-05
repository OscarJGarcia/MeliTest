package com.apimercadolibre.test.coupon.service;

import java.util.*;

import com.apimercadolibre.test.coupon.model.NotFoundItems;
import com.apimercadolibre.test.coupon.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apimercadolibre.test.coupon.model.Item;
import com.apimercadolibre.test.coupon.model.ItemRequest;
import com.apimercadolibre.test.coupon.model.ItemResponse;

@Service
public class CouponServiceImpl implements ICouponService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public ItemResponse getTotalItems(ItemRequest items) {
        List<ItemResponse> result = new ArrayList<ItemResponse>();
        List<Item> mapItems = new ArrayList<Item>();
        List<String> notFound = new ArrayList<String>();
        ItemResponse finalResult = null;
        fillItemList(items.getItemIds(), notFound, mapItems);
        if (notFound.size() == items.getItemIds().size()) {
            return createErrorResponse(Constants.Messages.NOT_ITEM.getMessages(), notFound);
        }
        Float minValue = Collections.max(mapItems, Comparator.comparing(s -> s.getPrice())).getPrice();
        if (minValue > items.getAmount()) {
            return createErrorResponse(Constants.Messages.MIN_AMOUNT.getMessages(), items.getItemIds());
        }
        mapItems.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        Float max = Float.valueOf(0);
        getResult(result, new ArrayList<String>(), mapItems, items.getAmount(), items.getAmount(), max, 0);
        finalResult = Collections.max(result, Comparator.comparing(s -> s.getTotal()));
        if (!notFound.isEmpty()) {
            NotFoundItems notFoundItems = new NotFoundItems(Constants.Messages.NOT_FOUND.getMessages(), notFound);
            finalResult.setNotFoundItems(notFoundItems);
        }
        return finalResult;
    }

    private void fillItemList(List<String> items, List<String> notFound, List<Item> mapItems) {
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(items);
        ArrayList<String> itemsWithoutDuplicates = new ArrayList<>(hashSet);
        for (String itemId : itemsWithoutDuplicates) {
            Item item = getItemFromMeli(itemId);
            if (item != null) {
                mapItems.add(item);
            } else {
                notFound.add(itemId);
            }
        }
    }

    public ItemResponse createErrorResponse(String message, List<String> items) {
        ItemResponse finalResult = new ItemResponse(new ArrayList<>(), Float.valueOf(0));
        NotFoundItems notFoundItems = new NotFoundItems(message, items);
        finalResult.setNotFoundItems(notFoundItems);
        return finalResult;
    }

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        List<ItemResponse> result = new ArrayList<ItemResponse>();
        List<Item> mapItems = mapToItemList(items);
        mapItems.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        Float max = Float.valueOf(0);
        getResult(result, new ArrayList<String>(), mapItems, amount, amount, max, 0);
        List<String> finalResult = Collections.max(result, Comparator.comparing(s -> s.getTotal())).getItemIds();
        return finalResult;
    }

    private void getResult(List<ItemResponse> result, List<String> aux, List<Item> items, Float amount, Float totalAmount, Float max, int init) {
        if (amount > 0) {
            for (int i = init; i < items.size() && items.get(i).getPrice() <= amount; i++) {
                if (!aux.contains(items.get(i).getId())) {
                    aux.add(items.get(i).getId());
                    Float auxAmount = amount - items.get(i).getPrice();
                    if (totalAmount - auxAmount > max) {
                        max = totalAmount - auxAmount;
                        result.add(new ItemResponse(new ArrayList<String>(aux), max));
                    }
                    getResult(result, aux, items, auxAmount, totalAmount, max, i);
                    aux.remove(aux.size() - 1);
                }
            }
        } else if (amount == 0) {
            result.add(new ItemResponse(new ArrayList<String>(aux), max));
        }
    }

    public List<Item> mapToItemList(Map<String, Float> items) {
        List<Item> result = new ArrayList<Item>();
        for (Map.Entry<String, Float> item : items.entrySet()) {
            result.add(new Item(item.getKey(), item.getValue()));
        }
        return result;
    }

    @Override
    public Item getItemFromMeli(String id) {
        String url = "https://api.mercadolibre.com/items/".concat(id);
        Item item = null;
        try {
            item = restTemplate.getForObject(
                    url,
                    Item.class);
        } catch (Exception e) {
            return null;
        }
        return item;
    }


}
