/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.pojo;


/**
 *
 * @author srathod
 */
public class PizzaTopping {

    int toppingId;
    String pizzaTopping;
    int pizzaToppingQuantity;

    public PizzaTopping() {
    }

    public PizzaTopping(int toppingId, String pizzaTopping) {
        this.toppingId = toppingId;
        this.pizzaTopping = pizzaTopping;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getPizzaTopping() {
        return pizzaTopping;
    }

    public void setPizzaTopping(String pizzaTopping) {
        this.pizzaTopping = pizzaTopping;
    }

    public int getPizzaToppingQuantity() {
        return pizzaToppingQuantity;
    }

    public void setPizzaToppingQuantity(int pizzaToppingQuantity) {
        this.pizzaToppingQuantity = pizzaToppingQuantity;
    }

}
