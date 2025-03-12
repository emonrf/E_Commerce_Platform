package com.mypackage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private Double totalPrice = 0.0;

    // Helper methods to manage cart items
    public void addCartItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
        calculateTotalPrice();
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
        calculateTotalPrice();
    }

    public void clearCart() {
        cartItems.clear();
        totalPrice = 0.0;
    }

    public void calculateTotalPrice() {
        this.totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Method to convert cart to order
    public Order checkout(String shippingAddress, String paymentMethod) {
        Order order = new Order();
        order.setUser(this.user);
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);

        // Convert each cart item to an order item
        for (CartItem cartItem : this.cartItems) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            order.addItem(orderItem);
        }

        return order;
    }
}