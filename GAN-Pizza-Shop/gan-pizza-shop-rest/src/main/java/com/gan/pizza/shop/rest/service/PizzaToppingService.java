package com.gan.pizza.shop.rest.service;

import com.gan.pizza.shop.rest.pojo.PizzaTopping;
import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

@Component
public interface PizzaToppingService {
    
	//public void sendMail(String to, String from, String subject, String text, String attachmentPath) throws MessagingException;
    public String checkForToppingQuanity (PizzaTopping pizzaTopping);

}
