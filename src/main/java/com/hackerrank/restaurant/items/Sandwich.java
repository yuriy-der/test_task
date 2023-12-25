package com.hackerrank.restaurant.items;

public abstract class Sandwich implements Item{

    /**
     *
     * Sets the category to snacks
     */
    protected final Category category = Category.Snacks;

    /**
     *
     * Sets the packaging material to wrapper
     */
    protected final Container container = Container.Wrapper;
}
