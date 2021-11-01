/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.envProperties;

import com.gan.pizza.shop.rest.pojo.ToppingsCsv;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

/**
 *
 * @author srathod
 */
@Component
public class EnvironmentProperties implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext c) {
        ConfigurableEnvironment environment = c.getEnvironment();

        Map<String, Object> propertySource = new HashMap<>();
        List<ToppingsCsv> arrayList = null;
        try {
            CSVReader csvReader = new CSVReader(new FileReader("..\\src\\main\\resources\\Java_Ingredients_Testing.csv")); // For Jar file Path
            // CSVReader csvReader = new CSVReader(new FileReader("src\\main\\resources\\Java_Ingredients_Testing.csv")); // For Debug in IDE path
            arrayList = new CsvToBeanBuilder<ToppingsCsv>(csvReader)
                    .withType(ToppingsCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)// Skip the header line
                    .build()
                    .parse();
            //System.out.println(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ToppingsCsv topping : arrayList) {
            propertySource.put(topping.getIngredient(), topping.getQuantity());
            environment.getPropertySources().addFirst(new MapPropertySource("myToppingList", propertySource));
        }

        MutablePropertySources env = environment.getPropertySources();
        System.out.println("Pizza topping CSV read has been completed before starting Pizza Order REST API.");
        System.out.println("cheese: " + env.get("myToppingList").getProperty("cheese").toString());
        System.out.println("sausage: " + env.get("myToppingList").getProperty("sausage").toString());
        System.out.println("mushroom: " + env.get("myToppingList").getProperty("mushroom").toString());
        System.out.println("pineapples: " + env.get("myToppingList").getProperty("pineapples").toString());
        System.out.println("ham: " + env.get("myToppingList").getProperty("ham").toString());
        System.out.println("jalapeno: " + env.get("myToppingList").getProperty("jalapeno").toString());
        System.out.println("pepperoni: " + env.get("myToppingList").getProperty("pepperoni").toString());

    }

}
