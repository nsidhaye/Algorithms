package com.nss.algorithms.mealproblem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 */
public class MealCapacity {

    /**
     * Count of can serve meal / available total meal.
     */
    private AtomicInteger totalAvailMeal;

    private ConcurrentHashMap<MealType, AtomicInteger> mealOptions = new ConcurrentHashMap<>();
    
    public MealCapacity(int totalMeal) {
        totalAvailMeal = new AtomicInteger(totalMeal);
    }
    
    public void addMealOption(MealType key, int count) {
        mealOptions.putIfAbsent(key, new AtomicInteger(count)); 
    }
    
    /**
     * Serve meal which reduce total count. 
     * @return
     */
    public int serveMeal() {
        return totalAvailMeal.decrementAndGet();
    }
    
    /**
     * If Restaurant serve specific meal option then serve count of specific & total will be affect.
     * @param key
     */
    public void serveMealOption(MealType key) {
        AtomicInteger mealOptionCount = mealOptions.get(key);
        if(mealOptionCount == null)  { 
            throw new RuntimeException("Meal Option is not Available"); //Create new Dedicated Exception. 
        }
        mealOptionCount.decrementAndGet();
        serveMeal();
        //Do we need synchronized block? Possibly yes for mealOptionCount.decrementAndGet() & serveMeal()
    }
    
    /**
     * If Restaurant serve specific meal option then serve count of specific & total will be affect.
     * @param key
     */
    public void serveMealOption(MealType key, int count) {
        AtomicInteger mealOptionCount = mealOptions.get(key);
        mealOptionCount.set(mealOptionCount.get() - count);
        totalAvailMeal.set(totalAvailMeal.get() - count);
        //Do we need synchronized block? Possibly yes for mealOptionCount.set() & totalAvailMeal.set()
    }
    
    /**
     * Update total meal count instead of decreasing one by one. 
     * @param totalmeal
     */
    public void updateTotalMeal(int totalmeal) {
        totalAvailMeal.set(totalmeal); 
    }
    
    /**
     * Get Available meals
     * @param mealType
     * @return
     */
    public int getAvailableMeals(MealType mealType) {
        AtomicInteger mealOptionCount = mealOptions.get(mealType);
        
        return mealOptionCount!= null ? mealOptionCount.get() : 0;
    }
    
    public int getTotalMealAvailable() {
        return totalAvailMeal.get();
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTotal Available Meal : ");
        sb.append(getTotalMealAvailable());
        for (Map.Entry<MealType, AtomicInteger> entry:  mealOptions.entrySet()) {
            sb.append("\nAvailable ");
            sb.append(entry.getKey().name());
            sb.append(" : ");
            sb.append(entry.getValue().get());
        }
        return sb.toString();
    }
}
