package com.example.demo.service;

import com.example.demo.dto.ProductDeliveryDTO;
import com.example.demo.models.productsdelivery.ProductDelivery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDeliveryService {

    public ProductDeliveryDTO findById(Long id);
    public ProductDeliveryDTO saveProductDelivery(ProductDelivery productDelivery);
    public ProductDeliveryDTO updateProductDelivery(ProductDelivery productDelivery);
    public Long deleteProductDelivery(Long id);
    public List<ProductDeliveryDTO> findAllProductDelivery();
    public void assignProductToDelivery(Long deliveryId, Long productId);
    public void removeProductFromPackage(Long deliveryId, Long productId);
}
