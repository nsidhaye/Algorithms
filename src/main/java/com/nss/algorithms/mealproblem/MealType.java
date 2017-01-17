package com.nss.algorithms.mealproblem;

/**
 * 
 */
public enum MealType {

    VEG("vegetarian",1), GLUTEN_FREE("gluten free",2), NUT_FREE("nut free",3), FISH_FREE("fish free",4);

    private int value;
    private String name;

    
    
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }



    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    private MealType(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    
}
