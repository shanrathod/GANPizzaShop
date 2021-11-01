/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.pojo;

import java.util.List;

/**
 *
 * @author srathod
 */
public class Pizza {

    int pizzaId;
    String pizzaName;
    List<PizzaTopping> pizzaToppings;

    public Pizza() {
    }

    public Pizza(int pizzaId, String pizzaName, List<PizzaTopping> pizzaToppings) {
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public List<PizzaTopping> getPizzaToppings() {
        return pizzaToppings;
    }

    public void setPizzaToppings(List<PizzaTopping> pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }

}
