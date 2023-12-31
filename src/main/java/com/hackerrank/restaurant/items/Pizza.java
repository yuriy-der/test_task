package com.hackerrank.restaurant.items;

public abstract class Pizza implements Item {

    /**
     *
     * Sets the category to main course
     */
    protected final Category category = Category.MainCourse;

    /**
     *
     * Sets the packaging material to box
     */
    protected final Container container = Container.Box;
}
