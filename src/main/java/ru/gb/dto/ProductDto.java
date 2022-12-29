package ru.gb.dto;

import ru.gb.model.Product;

public class ProductDto {       // обертка над Product для обмена с фронтом
    private Integer id;
    private String title;
    private Integer price;

    public ProductDto(Integer id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getprice();
    }
    public ProductDto() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getprice() {
        return price;
    }
    public void setprice(Integer price) {
        this.price = price;
    }
}
