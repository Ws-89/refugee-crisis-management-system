package com.example.demo.repo;

import com.example.demo.models.productsdelivery.ProductDelivery;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialResourceDeliveryRepo extends GenericRepository<ProductDelivery> {
}
