package com.apimercadolibre.test.coupon.model;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class NotFoundItems {

    private String message;

    public NotFoundItems(String message, List<String> items) {
        this.message = message;
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
