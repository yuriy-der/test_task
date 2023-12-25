package com.hackerrank.restaurant.order;

import com.hackerrank.restaurant.exceptions.NoSuchOrderException;
import com.hackerrank.restaurant.items.Item;

import java.util.Map;

public class OrderPayment {
    /**
     *
     * The customer id placing the order
     */
    private final long customerId;

    /**
     *
     * The order id
     */
    private final long orderId;

    /**
     *
     * @param customerId The customer id placing the order
     * @param orderId The order id
     */
    public OrderPayment(long customerId, long orderId) {
        this.customerId = customerId;
        this.orderId = orderId;
    }

    /**
     *
     * @return The total cost of the order
     * @throws NoSuchOrderException When the order does not exist
     */
    public double getPayableAmount() {
        Order order = PlaceOrder.placeOrder.getOrders(customerId).stream()
                .filter(o -> orderId == o.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchOrderException(String.format("Order (customer id: %s, order id: %s) does not exist.", customerId, orderId)));
        double totalCost = 0.0;
        for (Map.Entry<Item, Integer> entry : order.getItems().entrySet()) {
            double itemCost = entry.getKey().getCost() * entry.getValue();
            totalCost += itemCost;
        }
        return totalCost;
    }
}
