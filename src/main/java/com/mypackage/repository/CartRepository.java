package com.mypackage.repository;

import com.mypackage.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Find a cart by the associated user's ID
     * @param userId The ID of the user
     * @return An Optional containing the cart if found
     */
    Optional<Cart> findByUserId(Long userId);

    /**
     * Check if a cart exists for a specific user
     * @param userId The ID of the user
     * @return True if a cart exists, false otherwise
     */
    boolean existsByUserId(Long userId);
}