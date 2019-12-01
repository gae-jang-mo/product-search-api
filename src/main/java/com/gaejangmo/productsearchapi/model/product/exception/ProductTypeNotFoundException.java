package com.gaejangmo.productsearchapi.model.product.exception;

public class ProductTypeNotFoundException extends RuntimeException {
    public ProductTypeNotFoundException(String message) {
        super(message);
    }
}
