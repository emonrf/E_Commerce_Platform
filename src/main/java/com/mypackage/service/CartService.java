package com.mypackage.service;

import com.mypackage.entity.Cart;
import com.mypackage.entity.CartItem;
import com.mypackage.entity.Product;
import com.mypackage.entity.User;
import com.mypackage.repository.CartRepository;
import com.mypackage.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUser(User user) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(user.getId());

        if (cartOpt.isPresent()) {
            return cartOpt.get();
        } else {
            // Create a new cart if none exists
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        }
    }

    public Cart getOrCreateGuestCart() {
        Cart guestCart = new Cart();
        return cartRepository.save(guestCart);
    }

    public Cart addProductToCart(Cart cart, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if this product is already in the cart
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.containsProduct(product))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            existingItem.get().incrementQuantity(quantity);
        } else {
            // Add new cart item
            CartItem newItem = new CartItem(product, quantity);
            cart.addCartItem(newItem);
        }

        cart.calculateTotalPrice();
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Cart cart, Long productId) {
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cart.calculateTotalPrice();
        return cartRepository.save(cart);
    }

    public Cart updateProductQuantity(Cart cart, Long productId, Integer quantity) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    cart.calculateTotalPrice();
                });

        return cartRepository.save(cart);
    }

    public Cart clearCart(Cart cart) {
        cart.clearCart();
        return cartRepository.save(cart);
    }
}