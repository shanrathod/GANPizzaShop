/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.service;

import com.gan.pizza.shop.rest.pojo.Pizza;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author srathod
 */
public interface PizzaBakingService {

    public CompletableFuture<String> pizzaBakingProcess(Pizza pizza) throws InterruptedException;

}
