/**
 *
 */
package com.nss.algorithms.mealproblem;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * 
 */
@RunWith(JUnitPlatform.class)
public class OrderProcessingTest {

    @Test
    public void testOrderProcessing() {
        Restaurant a = new Restaurant("A", 5, 40);
        a.addMealOption(MealType.VEG, 4);
        
        Restaurant b = new Restaurant("B", 3, 100);
        b.addMealOption(MealType.VEG,20); 
        b.addMealOption(MealType.GLUTEN_FREE, 20);
        
        List<Restaurant> lstRestaurants = new ArrayList<>();
        lstRestaurants.add(a);
        lstRestaurants.add(b);
        
        Order order = new Order();
        order.setTotalMeal(50);
        order.addSpecificOrder(MealType.VEG, 5);
        order.addSpecificOrder(MealType.GLUTEN_FREE, 7);
        
        List<Order> lstOrder = new ArrayList<>();
        lstOrder.add(order);
        
        OrderProcessing orderProcessing = new OrderProcessing();
        
        ResultOrder resultOrder = orderProcessing.process(lstRestaurants, lstOrder);
        
        System.out.println(resultOrder);
        
//        assertTrue(resultOrder.get);
    }
    
}
