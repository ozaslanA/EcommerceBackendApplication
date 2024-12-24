package com.ozaslan.e_commerce_backend.repository;

import com.ozaslan.e_commerce_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
