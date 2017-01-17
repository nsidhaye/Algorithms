/**
 *
 */
package com.nss.algorithms.mealproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class ResultOrder {

    private Map<String, Order> fulfillOrders = new HashMap<>();

    /**
     * @return the fulfillOrders
     */
    public Map<String, Order> getFulfillOrders() {
        return fulfillOrders;
    }

    /**
     * @param fulfillOrders
     *            the fulfillOrders to set
     */
    public void setFulfillOrders(Map<String, Order> fulfillOrders) {
        this.fulfillOrders = fulfillOrders;
    }

    /**
     * 
     * @param restaurantName
     * @param order
     */
    public void addOrder(String restaurantName, Order order) {
        fulfillOrders.put(restaurantName, order);
    }

    /**
     * 
     * @param restaurantName
     * @param order
     */
    public void addOrder(String restaurantName, MealType mealType, int count) {
        Order order = null;
        if (fulfillOrders.containsKey(restaurantName)) {
            order = fulfillOrders.get(restaurantName);
            int oldCnt = 0;
            if (order.getSpecificOrders() != null) {
                Integer orderCnt = order.getSpecificOrders().get(mealType);
                if (orderCnt == null) {
                    orderCnt = 0;
                }
                oldCnt = orderCnt.intValue();
            }
            order.addSpecificOrder(mealType, count + oldCnt);
        }
        else {
            order = new Order();
            order.addSpecificOrder(mealType, count);
        }

        fulfillOrders.put(restaurantName, order);
    }

    /**
     * 
     * @param restaurantName
     * @param order
     */
    public void addOrder(String restaurantName, int totalMeal) {
        Order order = null;
        if (fulfillOrders.containsKey(restaurantName)) {
            order = fulfillOrders.get(restaurantName);
            order.setTotalMeal(order.getTotalMeal() + totalMeal);
        }
        else {
            order = new Order();
            order.setTotalMeal(totalMeal);
        }
        fulfillOrders.put(restaurantName, order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        int i =0;
        
        for (Map.Entry<String, Order> entry : getFulfillOrders().entrySet()) {
            if(i!=0) {
                sb.append(", ");
            }
            i++;
            sb.append("Restaurant ");
            sb.append(entry.getKey());
            sb.append(" (");
            Order order = entry.getValue();

            for (Map.Entry<MealType, Integer> orderentry : order.getSpecificOrders().entrySet()) {
                
                sb.append(orderentry.getValue());
                sb.append(" ");
                sb.append(orderentry.getKey().getName());
                sb.append(" + ");
            }
            
            sb.append(order.getTotalMeal());
            sb.append(" others");
            sb.append(")");
        }

        return sb.toString();
    }
}
