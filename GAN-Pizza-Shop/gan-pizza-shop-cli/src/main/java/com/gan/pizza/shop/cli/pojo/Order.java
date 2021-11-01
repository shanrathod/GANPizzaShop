/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.cli.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 *
 * @author srathod
 */
@JacksonXmlRootElement(localName = "Order")
public class Order {

    int orderId;
    String orderStatus;
    String pizzDoneMessage;
    List<Pizza> orderedPizzas;
    CustomerTable customerTableInfo;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @JacksonXmlElementWrapper(localName = "OrderedPizzaList")
    @JacksonXmlProperty(localName = "OrderedPizza")
    public List<Pizza> getOrderedPizzas() {
        return orderedPizzas;
    }

    public void setOrderedPizzas(List<Pizza> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }

    public CustomerTable getCustomerTableInfo() {
        return customerTableInfo;
    }

    public void setCustomerTableInfo(CustomerTable customerTableInfo) {
        this.customerTableInfo = customerTableInfo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPizzDoneMessage() {
        return pizzDoneMessage;
    }

    public void setPizzDoneMessage(String pizzDoneMessage) {
        this.pizzDoneMessage = pizzDoneMessage;
    }

}
