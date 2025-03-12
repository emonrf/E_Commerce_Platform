package com.mypackage.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    private Integer quantity = 1;

    // Constructor for convenience
    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Method to increase quantity
    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }

    // Method to check if this cart item contains a specific product
    public boolean containsProduct(Product product) {
        return this.product.getId().equals(product.getId());
    }
}