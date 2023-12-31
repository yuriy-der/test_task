package com.hackerrank.restaurant.items;

public abstract class ColdBeverages implements Item{

    /**
     *
     * Sets the category to cold beverages
     */
    protected final Category category = Category.ColdBeverages;

    /**
     *
     * Sets the packaging material to glass
     */
    protected final Container container = Container.Glass;
}
