/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gan.pizza.shop.cli;

import com.gan.pizz.shop.restclient.RestClient;
import com.gan.pizza.shop.cli.pojo.CustomerTable;
import com.gan.pizza.shop.cli.pojo.Order;
import com.gan.pizza.shop.cli.pojo.Pizza;
import com.gan.pizza.shop.cli.pojo.PizzaTopping;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srathod
 */
public class OrderingLogic {

    private static final Logger logger = LoggerFactory.getLogger(OrderingLogic.class);

    public static void OrerPizza() {
        int customerTableNumber = 0;
        String customerName = null;
        Scanner keyboard = new Scanner(System.in);
        CustomerTable customerTableInfo = new CustomerTable();
        RestClient restClient = new RestClient();
        //int pizzaOrderId = 123456;
        Random rand = new Random();
        Order pizzaOrder = new Order();
        Order removePizzaOrderList = new Order();

        try {

            do {

                boolean customerNameFlag = true;
                do {
                    System.out.println();
                    System.out.println();
                    System.out.println("Welcome To GAN Pizza Shop!");
                    System.out.println();

                    System.out.println("Please enter your name to begin Pizza Order: ");
                    customerName = keyboard.nextLine();

                    if (!customerName.isEmpty()) {
                        customerTableInfo.setCustomerName(customerName);
                        customerTableInfo.setTableNumber(customerTableNumber + 1);

                        customerNameFlag = true;
                    } else {
                        System.out.println("Order can not processed without Customer name.");
                        customerNameFlag = false;
                    }

                } while (!customerNameFlag);

                String continueOrderFlag = "N";
                ArrayList<Pizza> orderedPizzalist = new ArrayList();
                Pizza newPizza = null;
                ArrayList<PizzaTopping> newPizzaToppingList = new ArrayList<>();
                PizzaTopping newPizzaTopping = null;
                boolean morePizzaOrderFlag = false;
                String morePizzaOrder = null;

                do {

                    do {

                        System.out.println();

                        ArrayList<Pizza> displayPizzaList = new ArrayList();
                        displayPizzaList.add(new Pizza(0, "Veggie Pizza", new ArrayList()));
                        displayPizzaList.add(new Pizza(1, "Peperoni Pizza", new ArrayList()));
                        displayPizzaList.add(new Pizza(2, "BBQ Pizza", new ArrayList()));
                        displayPizzaList.add(new Pizza(3, "Cheese Pizza", new ArrayList()));

                        //Pizza Selection logic
                        System.out.println("To Order Pizza please enter pizza number from below pizza list.");
                        System.out.println();

                        displayPizzaList.forEach(pizza -> {
                            System.out.println(pizza.getPizzaId() + " " + pizza.getPizzaName());
                        });

                        boolean pizzaChoiceTrueFalse = false;
                        int choosenPizza;
                        do {
                            System.out.println("Enter your Pizza number & hit Enter: ");
                            choosenPizza = Integer.parseInt(keyboard.nextLine());

                            if (choosenPizza >= 0 && choosenPizza <= 3) {
                                //System.out.println("Order pizza logic goes here ");
                                newPizza = displayPizzaList.get(choosenPizza);
                                pizzaChoiceTrueFalse = true;
                            } else {
                                System.out.println(choosenPizza + " is wrong choice, please select correct number between 0 to 3 from above Pizza list.");
                                pizzaChoiceTrueFalse = false;
                            }
                        } while (!pizzaChoiceTrueFalse);

                        System.out.println();
                        System.out.println();

                        // Pizza topping logic
                        System.out.println("Please enter pizza toping number from below topping list: ");

                        System.out.println();
                        ArrayList<PizzaTopping> toppingList = new ArrayList<>();
                        toppingList.add(new PizzaTopping(0, "Cheese"));
                        toppingList.add(new PizzaTopping(1, "Sausage"));
                        toppingList.add(new PizzaTopping(2, "Mushroom"));
                        toppingList.add(new PizzaTopping(3, "Pineapples"));
                        toppingList.add(new PizzaTopping(4, "Ham"));
                        toppingList.add(new PizzaTopping(5, "Jalapeno"));
                        toppingList.add(new PizzaTopping(6, "Pepperoni"));

                        toppingList.forEach(topping -> {
                            System.out.println(topping.getToppingId() + " " + topping.getPizzaTopping());
                        });

                        String toppingDecision = "N";
                        do {

                            boolean choosenToppingTrueFalse = false;
                            int choosenTopping;

                            do {
                                System.out.println("Enter your pizza topping number & hit enter: ");
                                choosenTopping = Integer.parseInt(keyboard.nextLine());

                                if (choosenTopping >= 0 && choosenTopping <= 6) {
                                    // System.out.println("Topping logic goes here");

                                    newPizzaTopping = toppingList.get(choosenTopping);

                                    choosenToppingTrueFalse = true;
                                } else {
                                    System.out.println(choosenTopping + " is wrong choice, please select correct number between 0 to 6 from above Topping list.");
                                    choosenToppingTrueFalse = false;
                                }

                            } while (!choosenToppingTrueFalse);

                            System.out.println("Enter topping quantity for " + toppingList.get(choosenTopping).getPizzaTopping() + " & hit enter:");
                            int choosenToppingQuanity = Integer.parseInt(keyboard.nextLine());
                            newPizzaTopping.setPizzaToppingQuantity(choosenToppingQuanity);

                            String isEnoughToppingQuantity = restClient.checkPizzaToppings(newPizzaTopping);
                            System.out.println();
                            if (isEnoughToppingQuantity.equalsIgnoreCase("N")) {
                                System.out.println("Topping Quantity is insufficient than ordred for choosen topping: " + newPizzaTopping.getPizzaTopping() + "."
                                        + "Please enter less topping quantity for " + newPizzaTopping.getPizzaTopping() + " or choose other toppings.");
                                newPizzaTopping = null;
                            }
                            System.out.println();
                            //At this point I need to check if there is enough quantity available at REST side before placing the Order.
                            System.out.println("Would you like to add more toppings? Y/N");
                            toppingDecision = keyboard.nextLine();

                            if (isEnoughToppingQuantity.equalsIgnoreCase("Y")) {
                                newPizzaToppingList.add(newPizzaTopping);
                            }

                        } while (toppingDecision.equalsIgnoreCase("Y"));

                        newPizza.setPizzaToppings(newPizzaToppingList);
                        orderedPizzalist.add(newPizza);

                        System.out.println("Would you like to add more Pizza in Order? Y/N");

                        continueOrderFlag = keyboard.nextLine();

                        System.out.println();
                        System.out.println();
                        System.out.println();

                    } while (continueOrderFlag.equalsIgnoreCase("Y"));

                    pizzaOrder.setOrderId(rand.nextInt(1000));
                    pizzaOrder.setOrderedPizzas(orderedPizzalist);
                    pizzaOrder.setCustomerTableInfo(customerTableInfo);

                    //At this point I need to send ordes to REST project
                    System.out.println();
                    System.out.println("You Pizza Order with Order Id: " + pizzaOrder.getOrderId() + " is in process. Please wait....");
                    System.out.println();

                    pizzaOrder = restClient.sendPizzaOrder(pizzaOrder);

                    // Disaply message on CLI to user for order
                    if (pizzaOrder.getOrderStatus().equalsIgnoreCase("Completed") && !pizzaOrder.getPizzDoneMessage().isEmpty()) {
                        System.out.println();
                        System.out.println("You Pizza Order with Order Id: " + pizzaOrder.getOrderId() + " has been Completed. " + pizzaOrder.getPizzDoneMessage());
                    } else {
                        System.out.println();
                        System.out.println("Something went wrong while Ordering Pizza with Order Id:" + pizzaOrder.getOrderId() + " Please continue your order.");
                    }

                    System.out.println();
                    System.out.println("Would you like to place more Pizza Order? Y/N");

                    morePizzaOrder = keyboard.nextLine();

                    if (morePizzaOrder.equalsIgnoreCase("Y")) {
                        morePizzaOrderFlag = true;
                    } else {
                        morePizzaOrderFlag = false;
                    }
                    // Emptying Order variable for next order
                    removePizzaOrderList = pizzaOrder;

                    pizzaOrder.getOrderedPizzas().removeAll(removePizzaOrderList.getOrderedPizzas());
                    pizzaOrder.setOrderId(0);
                    pizzaOrder.setOrderStatus(null);
                    pizzaOrder.setPizzDoneMessage(null);
                    pizzaOrder.getCustomerTableInfo().setCustomerName(null);
                    pizzaOrder.getCustomerTableInfo().setTableNumber(0);
                } while (morePizzaOrderFlag);

            } while (true);
        } catch (Exception e) {
            System.out.println("Error Occured during pizza Ordering. Error is: " + e);
        }

    }

}
