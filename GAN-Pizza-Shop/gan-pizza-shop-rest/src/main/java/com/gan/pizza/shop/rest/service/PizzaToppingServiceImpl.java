package com.gan.pizza.shop.rest.service;

import com.gan.pizza.shop.rest.pojo.PizzaTopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;

@Service
public class PizzaToppingServiceImpl implements PizzaToppingService {

    @Autowired
    ConfigurableEnvironment environment;

    @Override
    public String checkForToppingQuanity(PizzaTopping pizzaTopping) {

        MutablePropertySources env = environment.getPropertySources();
        String toppingCheckResult = "Y";
        String quantity = env.get("myToppingList").getProperty(pizzaTopping.getPizzaTopping().toLowerCase()).toString();
        
        int availbleQuantity = Integer.parseInt(quantity);

        int orderedToppingQty = pizzaTopping.getPizzaToppingQuantity();

        int remainingQty = availbleQuantity - orderedToppingQty;

        if (remainingQty < 0) {
            toppingCheckResult = "N";
        }

        return toppingCheckResult;
    }

}
