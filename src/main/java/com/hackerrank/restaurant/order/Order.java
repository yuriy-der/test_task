package com.hackerrank.restaurant.order;

import com.hackerrank.restaurant.exceptions.BadQuantityException;
import com.hackerrank.restaurant.exceptions.DuplicateItemEntryException;
import com.hackerrank.restaurant.exceptions.NoSuchItemException;
import com.hackerrank.restaurant.exceptions.NotEnoughItemException;
import com.hackerrank.restaurant.inventory.Inventory;
import com.hackerrank.restaurant.items.Item;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    /**
     *
     * The order id
     */
    private final long id;

    /**
     *
     * A map to keep track of all the items in the order and the quantity
     */
        private final Map<Item, Integer> items;

    /**
     *
     * @param id The order id
     */
    public Order(long id) {
        this.id = id;
        this.items = new HashMap<>();
    }

    /**
     *
     * @return The order id
     */
    public long getId() {
        return this.id;
    }

    /**
     *
     * @param item The item to be added in the order
     * @param quantity The added quantity
     * @throws BadQuantityException When the quantity is less than or equal to zero
     * @throws NoSuchItemException When the item is not available
     * @throws NotEnoughItemException When the updated quantity is greater than the quantity in the inventory
     * @throws DuplicateItemEntryException When adding an existing item in the order
     */
    public void addItem(Item item, int quantity) {
        if (quantity <= 0) throw new BadQuantityException(String.format("Expecting a value greater than zero but %s found.", quantity));
        if (Inventory.inventory.getQuantity(item) == 0) throw new NoSuchItemException(String.format("Item '%s' is not available.", item.getName()));
        if (items.containsKey(item)) throw new DuplicateItemEntryException(String.format("Cannot add duplicate item '%s'.", item.getName()));
        Inventory.inventory.decrementQuantity(item, quantity);
        items.put(item, quantity);
    }

    /**
     *
     * @param item The item to be removed from the order
     * @throws NoSuchItemException When the item is not available
     */
    public void removeItem(Item item) {
        if (!items.containsKey(item)) throw new NoSuchItemException(String.format("Item '%s' is not available.", item.getName()));
        Inventory.inventory.incrementQuantity(item, items.get(item));
        items.remove(item);
    }

    /**
     *
     * @param item The item in the order
     * @param quantity The expected quantity of the increment
     * <pre>
     * {@code updatedQuantity = currentQuantity + quantity;}
     * </pre>
     * @throws BadQuantityException When the quantity is less than or equal to zero
     * @throws NoSuchItemException When the item is not available
     * @throws NotEnoughItemException When the updated quantity is greater than the quantity in the inventory
     */
    public void incrementQuantity(Item item, int quantity) {
        if (quantity <= 0) throw new BadQuantityException(String.format("Expecting a value greater than zero but %s found.", quantity));
        if (!items.containsKey(item)) throw new NoSuchItemException(String.format("Item '%s' is not available.", item.getName()));
        Inventory.inventory.decrementQuantity(item, quantity);
        items.put(item, (items.getOrDefault(item, 0)) + quantity);
    }

    /**
     *
     * @param item The item in the order
     * @param quantity The expected quantity of the decrement
     * <pre>
     * {@code updatedQuantity = currentQuantity - quantity;}
     * </pre>
     * @throws BadQuantityException When the quantity is less than or equal to zero
     * @throws NoSuchItemException When the item is not available
     * @throws NotEnoughItemException When the updated quantity is less than zero
     */
    public void decrementQuantity(Item item, int quantity) {
        if (quantity <= 0) throw new BadQuantityException(String.format("Expecting a value greater than zero but %s found.", quantity));
        if (!items.containsKey(item)) throw new NoSuchItemException(String.format("Item '%s' is not available.", item.getName()));
        if (items.get(item) < quantity) throw new NotEnoughItemException(String.format("Not enough quantity for the item '%s'.", item.getName()));
        Inventory.inventory.incrementQuantity(item, quantity);
        items.put(item, items.get(item) - quantity);
    }

    /**
     *
     * @return All the items and added quantity
     */
    public Map<Item, Integer> getItems() {
        return this.items;
    }

    /**
     *
     * @return All the items name, quantity, cost per unit, and total cost
     * separated by space each on a separate line. The items on each line must
     * be in the lexicographical order. For example,
     * <pre>
     * {@code
     * Latte 3 $3.78 $11.34
     * Mulled Cider 2 $3.32 $6.64
     * Spicy Apple Cider 2 $3.21 $6.42
     * }
     * </pre>
     */
    public String printOrder() {
        return items.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted(Comparator.comparing(entry -> entry.getKey().getName()))
                .map(e -> String.format("%s %s $%.2f $%.2f", e.getKey().getName(), e.getValue(), e.getKey().getCost(), e.getKey().getCost() * e.getValue()))
                .collect(Collectors.joining("\n"));

    }
}
