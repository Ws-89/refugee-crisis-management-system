package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.products.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        Product result = productRepository.findById(product.getProductId())
                .map(p -> {
                        Product newProduct = p;
                            newProduct.setName(product.getName());
                            newProduct.setDescription(product.getDescription());
                            newProduct.setExpirationDate(product.getExpirationDate());
                            newProduct.setWeight(product.getWeight());
                            newProduct.setAmount(product.getAmount());
                            newProduct.setReserved(product.getReserved());
                            newProduct.setFragile(product.isFragile());
                            newProduct.setState(product.getState());
                            newProduct.setCategory(product.getCategory());
                            newProduct.setCargo(product.getCargo());
                            return newProduct;
                })
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found", product.getProductId())));

        return ProductMapper.INSTANCE.productToProductDTO(productRepository.save(result));

    }

    @Override
    public Long deleteProduct(Long id) {
        return productRepository.findById(id).map(p -> {
            productRepository.delete(p);
            return p.getProductId();
        }).orElseThrow(() -> new NotFoundException(String.format("Product with id %d not found", id)));
    }

    @Override
    public Page<ProductDTO> findByNameContaining(String name, int page, int size) {
//        return productRepository.findAll().stream().map(p -> ProductMapper.INSTANCE.productToProductDTO(p)).collect(Collectors.toList());
        return productRepository.findByNameContaining(name, PageRequest.of(page, size)).map(p -> ProductMapper.INSTANCE.productToProductDTO(p));
}
}
