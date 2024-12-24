package com.ozaslan.e_commerce_backend.repository;

import com.ozaslan.e_commerce_backend.model.Cart;
import com.ozaslan.e_commerce_backend.model.CartItem;
import com.ozaslan.e_commerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.cart = :cart AND c.product = :product AND c.size = :size AND c.userId = :userId")
    Optional<CartItem> isCartItemExist(@Param("cart") Cart cart,
                                    @Param("product") Product product,
                                    @Param("size") String size,
                                    @Param("userId") Long userId);


}
