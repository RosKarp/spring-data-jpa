package ru.gb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }
    public Product() { }

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

    @Override
    public String toString() {
        return "id = " + id + "   " + title + "   price = " + price;
    }
}