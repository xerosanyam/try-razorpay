package com.example.razorpay.repository;

import com.example.razorpay.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ProductOrder,Long> {
    List<ProductOrder> findByAmountEquals(Double amount);
    ProductOrder findById(long id);
}
