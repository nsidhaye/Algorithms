package com.nss.algorithms.mealproblem;

/**
 * 
 */
public class Restaurant {
    /**
     * Name of Restaurant
     */
    private String name;
    
    /** 
     * Rating out of 5.
     */
    private int rating;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    private MealCapacity mealCapacity;
    
    /**
     * Constructor
     * @param name
     * @param totalAvailMeal
     */
    public Restaurant(String name, int rating, int totalAvailMeal) {
        this.name = name;
        this.rating = rating;        
        mealCapacity = new MealCapacity(totalAvailMeal);
    }
    
    public void addMealOption(MealType mealType, int count) {
        mealCapacity.addMealOption(mealType, count); 
    } 
    
    /**
     * Return MealCapacity. Think of returning deep copy.????
     * @return
     */
    public MealCapacity getMealCapacity() {
        return mealCapacity;
    }
    
    /**
     * Get Available meals
     * @param mealType
     * @return
     * /
    public int getAvailableMeals(MealType mealType) {
        return mealCapacity.getAvailableMeals(mealType);
    }
    */
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\nRating: ");
        sb.append(rating);
        sb.append(getMealCapacity());
        return sb.toString();
    }
}
