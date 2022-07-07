package com.example.demo.service;

import com.example.demo.models.productsdelivery.ProductDelivery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDeliveryService {

    public ProductDelivery findById(Long id);
    public ProductDelivery saveProductDelivery(ProductDelivery productDelivery);
    public ProductDelivery updateProductDelivery(ProductDelivery productDelivery);
    public List<ProductDelivery> selectProductDeliveryForDisplayingWithoutProducts();
    public Long deleteProductDelivery(Long id);
    public List<ProductDelivery> findAllProductDelivery();
    public void assignProductToDelivery(Long deliveryId, Long productId);
    public void removeProductFromPackage(Long deliveryId, Long productId);
}
