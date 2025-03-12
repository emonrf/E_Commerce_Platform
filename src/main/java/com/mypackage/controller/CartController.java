package com.mypackage.controller;

import com.mypackage.entity.Cart;
import com.mypackage.entity.User;
import com.mypackage.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // For demonstration purposes - in a real app, you'd get the user from security context
    // or session, or use a guest cart for non-authenticated users
    @GetMapping
    public ResponseEntity<Cart> getCart() {
        // For now, use a guest cart
        Cart cart = cartService.getOrCreateGuestCart();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/items")
    public ResponseEntity<Cart> addToCart(@RequestParam Long productId,
                                          @RequestParam(defaultValue = "1") Integer quantity) {
        Cart cart = cartService.getOrCreateGuestCart();
        cart = cartService.addProductToCart(cart, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long productId) {
        Cart cart = cartService.getOrCreateGuestCart();
        cart = cartService.removeProductFromCart(cart, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<Cart> updateQuantity(@PathVariable Long productId,
                                               @RequestParam Integer quantity) {
        Cart cart = cartService.getOrCreateGuestCart();
        cart = cartService.updateProductQuantity(cart, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping
    public ResponseEntity<Cart> clearCart() {
        Cart cart = cartService.getOrCreateGuestCart();
        cart = cartService.clearCart(cart);
        return ResponseEntity.ok(cart);
    }
}