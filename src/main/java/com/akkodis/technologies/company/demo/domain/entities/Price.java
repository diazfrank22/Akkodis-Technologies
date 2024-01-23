package com.akkodis.technologies.company.demo.domain.entities;

import java.time.LocalDateTime;

/**
 * El dominio o core: estas entidades no deben tener ningun tipo de validaciones, anotaciones, librerias externas como lombok,
 * dado que esta capa no debe depender de factores externos por que esta capa de deberia cambiar constantemente.
 * Lo que si podria contener son reglas de negocios que estemos seguro que no van a cambiar a corto plazo
 */
public class Price {

    private Long priceId;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private String priority;
    private Double price;
    private String currency;

    public Price(Long productid, Long brandId, Long priceList, LocalDateTime startDate, Double price, String priority) {
        this.productId = productid;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.price = price;
        this.priority = priority;
    }

    public Price(Long productid, Long brandId, Long priceList, LocalDateTime startDate, Double price) {
        this.productId = productid;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return "Price{" +
                "priceId=" + priceId +
                ", brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", productId=" + productId +
                ", priority='" + priority + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}