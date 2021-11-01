/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.pojo;

import com.opencsv.bean.CsvBindByName;


/**
 *
 * @author srathod
 */
public class ToppingsCsv {

    @CsvBindByName(column = "ingredient")
    String ingredient;
    @CsvBindByName(column = "quantity")
    int quantity;

    public ToppingsCsv() {
    }

    public ToppingsCsv(String ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
