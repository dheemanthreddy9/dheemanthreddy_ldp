package org.example.dto;

import java.math.BigDecimal;

public class ProductRequestDto {

    private String name;
    private String category;
    private BigDecimal price;
    private Boolean active = true;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, String category, BigDecimal price, Boolean active) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.active = active != null ? active : true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
