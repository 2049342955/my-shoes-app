package com.my.business.entity;

import java.util.Date;
import javax.persistence.*;

public class Discounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select REPLACE(uuid(),'-','')")
    private String id;

    @Column(name = "shoes_id")
    private String shoesId;

    private Double price;

    private String descriptionv;

    private Date date;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return shoes_id
     */
    public String getShoesId() {
        return shoesId;
    }

    /**
     * @param shoesId
     */
    public void setShoesId(String shoesId) {
        this.shoesId = shoesId;
    }

    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return descriptionv
     */
    public String getDescriptionv() {
        return descriptionv;
    }

    /**
     * @param descriptionv
     */
    public void setDescriptionv(String descriptionv) {
        this.descriptionv = descriptionv;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}