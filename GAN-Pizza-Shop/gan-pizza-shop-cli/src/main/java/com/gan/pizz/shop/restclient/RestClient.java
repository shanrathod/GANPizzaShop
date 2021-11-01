/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizz.shop.restclient;

import com.gan.pizza.shop.cli.pojo.Order;
import com.gan.pizza.shop.cli.pojo.PizzaTopping;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author srathod
 */
@Component
public class RestClient implements Serializable {

    final String REST_SERVICE_URI = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    public Order sendPizzaOrder(Order order) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Order> httpEntity = new HttpEntity<>(order, headers);

        Order orderResponse = new Order();

        try {
            orderResponse = restTemplate.postForObject(REST_SERVICE_URI + "pizzaOrder",
                    httpEntity, Order.class);
        } catch (RestClientException ex) {
            logger.info("Error occured in RestClient when trying to send Order to REST API. Error is: " + ex);
        }

        return orderResponse;

    }

    public String checkPizzaToppings(PizzaTopping topping) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PizzaTopping> httpEntity = new HttpEntity<>(topping, headers);

        String toppingResponse = null;

        try {
            toppingResponse = restTemplate.postForObject(REST_SERVICE_URI + "checkPizzaTopping",
                    httpEntity, String.class);
        } catch (RestClientException ex) {
            logger.info("Error occured in RestClient when trying to checkPizzaToppings from REST API. Error is: " + ex);
        }

        return toppingResponse;

    }

    public void test() {
        String mesage = restTemplate.getForObject(REST_SERVICE_URI + "test", String.class);

        System.out.println(mesage);

    }
}
