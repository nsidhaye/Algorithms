/**
 * We're ordering meals for a team lunch. Every member in the team needs one meal, some have dietary restrictions such as vegetarian, gluten free, nut
 * free, and fish free. We have a list of restaurants which serve meals that satisfy some of these restrictions. Each restaurant has a rating, and a
 * limited amount of meals in stock that they can make today. Implement an object oriented system with automated tests that can automatically produce
 * the best possible meal orders with reasonable assumptions. Example:
 * 
 * 
 * Team needs: total 50 meals including 5 vegetarians and 7 gluten free. Restaurants: Restaurant A has a rating of 5/5 and can serve 40 meals
 * including 4 vegetarians, Restaurant B has a rating of 3/5 and can serve 100 meals including 20 vegetarians, and 20 gluten free.
 * 
 * Expected meal orders: Restaurant A (4 vegetarian + 36 others), Restaurant B (1 vegetarian + 7 gluten free + 2 others)
 * 
 * 
 */
package com.nss.algorithms.mealproblem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In case of multiple orders create one consolidated order & then process it.
 */
public class OrderProcessing {

    /**
     * 
     * @param orders
     */
    private Order consolidateOrders(List<Order> orders) {
        Order consolidateOrder = new Order();
        int totalOrderMeal = 0;
        Map<MealType, Integer> consolidatedSpecialOrders = new HashMap<>();

        for (Order order : orders) {
            totalOrderMeal += order.getTotalMeal();
            Map<MealType, Integer> specificOrders = order.getSpecificOrders();

            for (Map.Entry<MealType, Integer> entry : specificOrders.entrySet()) {
                if (consolidatedSpecialOrders.containsKey(entry.getKey())) {
                    Integer oldValue = entry.getValue();
                    consolidatedSpecialOrders.put(entry.getKey(), entry.getValue() + oldValue);
                }
                else {
                    consolidatedSpecialOrders.put(entry.getKey(), entry.getValue());
                }
            }

        }

        consolidateOrder.setTotalMeal(totalOrderMeal);
        consolidateOrder.setSpecificOrders(consolidatedSpecialOrders);

        return consolidateOrder;
    }

    /**
     * 
     * @param lstRestaurants
     * @param orders
     */
    public ResultOrder process(List<Restaurant> lstRestaurants, List<Order> orders) {
        // For simplicity we will sort list of Restaurant with ranking.
        lstRestaurants.sort((Restaurant r1, Restaurant r2) -> r2.getRating() - r1.getRating());

        // Instead of list of order we will create one combined order & then process it.
        Order consolidatedOrder = consolidateOrders(orders);
        ResultOrder resultOrder = new ResultOrder();

        // For specific orders.
        for (Restaurant restaurant : lstRestaurants) {
            MealCapacity mealCapacity = restaurant.getMealCapacity();
            // later create order result too.

            Map<MealType, Integer> specificOrders = consolidatedOrder.getSpecificOrders();

            for (Map.Entry<MealType, Integer> entry : specificOrders.entrySet()) {
                MealType mealType = entry.getKey();
                Integer orderCount = entry.getValue();
                int availableSPMeal = mealCapacity.getAvailableMeals(mealType);

                if (availableSPMeal > 0 && orderCount > 0) {
                    if (availableSPMeal >= orderCount.intValue()) {
                        // System.out.println(restaurant.getName() + mealType.name() + orderCount);

                        // Decrease count:
                        mealCapacity.serveMealOption(mealType, orderCount);
                        entry.setValue(0);
                        consolidatedOrder.setTotalMeal(consolidatedOrder.getTotalMeal() - orderCount);

                        resultOrder.addOrder(restaurant.getName(), mealType, orderCount);
                        // System.out.println(consolidatedOrder);
                        // System.out.println(restaurant.getName() + mealType.name() + availableSPMeal + "/" + orderCount);
                    }
                    else {
                        // Restaurant partially able to fulfill this request.
                        // System.out.println(restaurant.getName() + mealType.name() + availableSPMeal + "/" + orderCount);
                        // Decrease count:
                        mealCapacity.serveMealOption(mealType, availableSPMeal);
                        entry.setValue(orderCount - availableSPMeal);
                        consolidatedOrder.setTotalMeal(consolidatedOrder.getTotalMeal() - availableSPMeal);

                        resultOrder.addOrder(restaurant.getName(), mealType, availableSPMeal);
                        // System.out.println(consolidatedOrder);
                        // System.out.println(restaurant.getName() + mealType.name() + orderCount + "/" + availableSPMeal);
                    }
                }
            }
        }

        // Another loop for other orders.
        for (Restaurant restaurant : lstRestaurants) {
            MealCapacity mealCapacity = restaurant.getMealCapacity();
            int totalAvailbleMeal = mealCapacity.getTotalMealAvailable();
            int totalReq = consolidatedOrder.getTotalMeal();

            if (totalAvailbleMeal > 0 && totalReq > 0) {
                if (totalAvailbleMeal >= totalReq) {
                    // System.out.println(restaurant);
                    // Decrease count
                    mealCapacity.updateTotalMeal(totalAvailbleMeal - totalReq);
                    consolidatedOrder.setTotalMeal(0);
                    // System.out.println(restaurant);
                    resultOrder.addOrder(restaurant.getName(), totalReq);

                }
                else {
                    // Restaurant partially able to fulfill this request.
                    // System.out.println(restaurant);
                    // Decrease count
                    mealCapacity.updateTotalMeal(0);
                    consolidatedOrder.setTotalMeal(totalReq - totalAvailbleMeal);

                    // System.out.println(restaurant);

                    resultOrder.addOrder(restaurant.getName(), totalAvailbleMeal);
                }
            }
        }

        return resultOrder;
    }
}
