package com.example.demo.service;

import com.example.demo.models.products.Product;

import java.util.List;

public interface MaterialResourceService  {

    List<Product> findAllByCategory(String category);

}
