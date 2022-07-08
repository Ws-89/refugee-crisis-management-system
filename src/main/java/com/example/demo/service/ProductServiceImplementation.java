package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
                .map(p -> ProductMapper.INSTANCE.productToProductDTO(p))
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", id)));
    }

    @Override
    public ProductDTO saveProduct(Product product) {
        Product p = productRepository.save(product);
        return ProductMapper.INSTANCE.productToProductDTO(p);
    }

    @Override
    public ProductDTO updateProduct(Product product) {
        return productRepository.findById(product.getProductId())
                .map(p -> {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setExpirationDate(product.getExpirationDate());
                    p.setWeight(product.getWeight());
                    p.setAmount(product.getAmount());
                    p.setReserved(product.getReserved());
                    p.setFragile(product.isFragile());
                    p.setState(product.getState());
                    p.setCategory(product.getCategory());
                    p.setProductDelivery(product.getProductDelivery());

                    productRepository.save(p);
                    return ProductMapper.INSTANCE.productToProductDTO(p);
                } )
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", product.getProductId())));
    }

    @Override
    public Long deleteProduct(Long id) {
        return productRepository.findById(id).map(p -> {
            productRepository.delete(p);
            return p.getProductId();
        }).orElseThrow(() -> new NotFoundException(String.format("Product with id %d not found", id)));
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream().map(p -> ProductMapper.INSTANCE.productToProductDTO(p)).collect(Collectors.toList());
}
}
