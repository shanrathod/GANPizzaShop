/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.rest.controller;

import com.gan.pizza.shop.rest.pojo.Order;
import com.gan.pizza.shop.rest.pojo.Pizza;
import com.gan.pizza.shop.rest.pojo.PizzaTopping;
import com.gan.pizza.shop.rest.service.PizzaBakingServiceImpl;
import com.gan.pizza.shop.rest.service.PizzaToppingServiceImpl;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author srathod
 */
@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    PizzaToppingServiceImpl pizzaToppingServiceImpl;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    PizzaBakingServiceImpl pizzaBakingServiceImpl;

    @RequestMapping(value = "/pizzaOrder", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)

    public ResponseEntity<Order> pizzaOrder(@RequestBody Order order) {

 
        String bakingMessage = sendPizzaforBaking(order.getOrderedPizzas());
        order.setOrderStatus("Completed");
        order.setPizzDoneMessage(bakingMessage);
        System.out.println("From Controller meessage: " + bakingMessage);
        
        Order orderResponse = order;

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    public String sendPizzaforBaking(List<Pizza> orderedPizzas) {
        CompletableFuture<String> ovens;
        String finalPizzaOrderMessage = "Your Ordered Pizza: ";

        for (Pizza pizza : orderedPizzas) {
            try {
                ovens = pizzaBakingServiceImpl.pizzaBakingProcess(pizza);
                finalPizzaOrderMessage +=  " " + ovens.get();
            } catch (Exception e) {
                System.out.println("error " + e);
            }
        }

        return finalPizzaOrderMessage;

    }

    @RequestMapping(value = "/checkPizzaTopping", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkPizzzaTopping(@RequestBody PizzaTopping pizzaTopping) throws MessagingException {

        String toppingCheckResponse = pizzaToppingServiceImpl.checkForToppingQuanity(pizzaTopping);

        return new ResponseEntity<>(toppingCheckResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testing() {

        String message = "This is test";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
