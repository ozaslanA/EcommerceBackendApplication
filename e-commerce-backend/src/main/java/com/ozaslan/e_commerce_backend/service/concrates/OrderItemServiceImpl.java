package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.model.OrderItem;
import com.ozaslan.e_commerce_backend.repository.OrderItemRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    public final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {

        return orderItemRepository.save(orderItem);
    }
}
