/**
 *
 */
package com.nss.algorithms.mealproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class Order {
    private int totalMeal;
    
    private Map<MealType, Integer> specificOrders = new HashMap<>();
    
    public Order() {
        // TODO Auto-generated constructor stub
    }
    
    public Order(int totalMeal) {
        this.totalMeal = totalMeal;
    }
    
    /**
     * 
     * @param mealType
     * @param count
     */
    public void addSpecificOrder(MealType mealType, int count) {
        specificOrders.put(mealType, Integer.valueOf(count));
    }
    
    /**
     * @return the totalMeal
     */
    public int getTotalMeal() {
        return totalMeal;
    }
    
    /**
     * @param totalMeal the totalMeal to set
     */
    public void setTotalMeal(int totalMeal) {
        this.totalMeal = totalMeal;
    }

    /**
     * @return the specificOrder
     */
    public Map<MealType, Integer> getSpecificOrders() {
        return specificOrders;
    }
    
    /**
     * @param specificOrders the specificOrders to set
     */
    public void setSpecificOrders(Map<MealType, Integer> specificOrders) {
        this.specificOrders = specificOrders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTotal Meal : ");
        sb.append(totalMeal);
        for (Map.Entry<MealType, Integer> entry:  specificOrders.entrySet()) {
            sb.append("\nAvailable ");
            sb.append(entry.getKey().name());
            sb.append(" : ");
            sb.append(entry.getValue());
        }
        return sb.toString(); 
    }
}
