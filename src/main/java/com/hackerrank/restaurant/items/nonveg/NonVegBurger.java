package com.hackerrank.restaurant.items.nonveg;

import com.hackerrank.restaurant.items.Category;
import com.hackerrank.restaurant.items.Container;
import com.hackerrank.restaurant.items.Sandwich;

public class NonVegBurger extends Sandwich {
    /**
     *
     * The item name
     */
    private final String name;

    /**
     *
     * The item cost per unit
     */
    private double cost;

    /**
     *
     * @param name The item name
     * @param cost The item cost per unit
     */
    public NonVegBurger(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     *
     * @return The item name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return The item cost per unit
     */
    public double getCost() {
        return this.cost;
    }

    /**
     *
     * @param cost The item updated cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     *
     * @return The item category
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @return The item container
     */
    public Container getContainer() {
        return container;
    }
}
