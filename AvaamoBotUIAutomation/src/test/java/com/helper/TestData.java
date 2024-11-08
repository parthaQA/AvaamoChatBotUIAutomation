package com.helper;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "orderPizza")
    public Object[][] orderPizza() {

        return new Object[][]{

                {"partha", "partha.senuwsb@gmail.com","I would like to order a non veg pizza", "Pepperoni", "Thick Crust", "Medium", "Excellent"},


        };
    }

    @DataProvider(name = "pizzaType")
    public Object[][] pizzaType() {

        return new Object[][]{

                {"partha", "partha.senuwsb@gmail.com","I would like to order a non veg pizza", "chicken tikka", "i am sorry ,  could'nt Understand , Try Again ."}


        };
    }

    @DataProvider(name = "botResponse")
    public Object[][] botResponse() {

        return new Object[][]{

                {"I"},


        };
    }
}
