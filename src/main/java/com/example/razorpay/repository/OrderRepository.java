package com.example.razorpay.repository;

import com.example.razorpay.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*
   Create a repository to save order information.
   Using Data Mapper Pattern
 */
public interface OrderRepository extends CrudRepository<ProductOrder,Long> {
    List<ProductOrder> findByAmountEquals(int amount);
    ProductOrder findById(String id);
}
