/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.service;

import com.gan.pizza.shop.rest.pojo.Pizza;
import com.gan.pizza.shop.rest.pojo.PizzaTopping;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

/**
 *
 * @author srathod
 */
@Service
public class PizzaBakingServiceImpl implements PizzaBakingService {

    @Autowired
    ConfigurableEnvironment environment;

    @Value("${spring.datasource.ovenBakingTime}")
    int ovenBakingTime;
    
    Logger logger = LoggerFactory.getLogger(PizzaBakingServiceImpl.class);

    @Override
    @Async
    public CompletableFuture<String> pizzaBakingProcess(Pizza pizza) {
        String pizzaBakingMessage = null;

        try {
            logger.info("Starting baking {} with oven number {}", pizza.getPizzaName(), Thread.currentThread().getName());
            Thread.sleep(ovenBakingTime);
            pizzaBakingMessage = pizza.getPizzaName() + " is Ready!";
            logger.info("Completed baking process for pizza {} with oven number {}", pizza.getPizzaName(), Thread.currentThread().getName());
            pizzaToppingAddUpdate(pizza);
        } catch (InterruptedException ex) {
            logger.info("Pizza baking was interrupted. Error is: " + ex);
            pizzaBakingMessage = "Pizza baking was interrupted. Error in Pizza baking Process.";
        }

        return CompletableFuture.completedFuture(pizzaBakingMessage);
    }

    private void pizzaToppingAddUpdate(Pizza pizza) {

        logger.info("Inside pizzaToppingAddUpdate Method.");

        MutablePropertySources env = environment.getPropertySources();
        String cheeseQuantity = env.get("myToppingList").getProperty("cheese").toString();
        String sausageQuantity = env.get("myToppingList").getProperty("sausage").toString();
        String mushroomQuantity = env.get("myToppingList").getProperty("mushroom").toString();
        String pineapplesQuantity = env.get("myToppingList").getProperty("pineapples").toString();
        String hamQuantity = env.get("myToppingList").getProperty("ham").toString();
        String jalapenoQuantity = env.get("myToppingList").getProperty("jalapeno").toString();
        String pepperoniQuantity = env.get("myToppingList").getProperty("pepperoni").toString();

        Map<String, Object> propertySource = new HashMap<>();
        Map<String, Object> storedToppingList = new HashMap<>();

        storedToppingList.put("cheese", Integer.parseInt(cheeseQuantity));
        storedToppingList.put("sausage", Integer.parseInt(sausageQuantity));
        storedToppingList.put("mushroom", Integer.parseInt(mushroomQuantity));
        storedToppingList.put("pineapples", Integer.parseInt(pineapplesQuantity));
        storedToppingList.put("ham", Integer.parseInt(hamQuantity));
        storedToppingList.put("jalapeno", Integer.parseInt(jalapenoQuantity));
        storedToppingList.put("pepperoni", Integer.parseInt(pepperoniQuantity));

        int remainingQuanity;

        for (PizzaTopping topping : pizza.getPizzaToppings()) {

            for (Map.Entry<String, Object> entry : storedToppingList.entrySet()) {

                if (entry.getKey().equalsIgnoreCase(topping.getPizzaTopping())) {
                    remainingQuanity = (int) entry.getValue() - topping.getPizzaToppingQuantity();
                    storedToppingList.replace(entry.getKey(), remainingQuanity);
                }

            }

        }

        propertySource = storedToppingList;
        environment.getPropertySources().addFirst(new MapPropertySource("myToppingList", propertySource));
//        System.out.println("Remaining Pizza Topping Quanitities after ordered Pizza is ready.");
//        System.out.println("Remaining cheese: " + env.get("myToppingList").getProperty("cheese").toString());
//        System.out.println("Remaining sausage: " + env.get("myToppingList").getProperty("sausage").toString());
//        System.out.println("Remaining mushroom: " + env.get("myToppingList").getProperty("mushroom").toString());
//        System.out.println("Remaining pineapples: " + env.get("myToppingList").getProperty("pineapples").toString());
//        System.out.println("Remaining ham:" + env.get("myToppingList").getProperty("ham").toString());
//        System.out.println("Remaining jalapeno: " + env.get("myToppingList").getProperty("jalapeno").toString());
//        System.out.println("Remaining pepperoni: " + env.get("myToppingList").getProperty("pepperoni").toString());

    }

}
