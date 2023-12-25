package com.hackerrank.restaurant.items;

public abstract class HotBeverages implements Item {

    /**
     *
     * Sets the category to hot beverages
     */
    protected final Category category = Category.HotBeverages;

    /**
     *
     * Sets the packaging material to cup
     */
    protected final Container container = Container.Cup;
}
