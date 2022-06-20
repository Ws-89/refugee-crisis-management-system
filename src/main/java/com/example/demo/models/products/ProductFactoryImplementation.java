package com.example.demo.models.products;

import org.springframework.stereotype.Service;

@Service
public class ProductFactoryImplementation {

    public static Product getInstance(ProductDTO source) {
        Product product = null;

        switch (source.getProductType()) {
            case Food:
                product = new FoodProduct(
                        source.getName(),
                        source.getExpirationDate(),
                        source.getDescription(),
                        source.getWeight(),
                        source.getAmount(),
                        source.isFragile(),
                        source.getState(),
                        source.getFoodType());
                break;

            case Hygiene:
                product = new HygieneProduct(
                        source.getName(),
                        source.getExpirationDate(),
                        source.getDescription(),
                        source.getWeight(),
                        source.getAmount(),
                        source.isFragile(),
                        source.getState(),
                        source.getHygienePurpose());
                break;

            case Medical:
                product = new MedicalProduct(
                        source.getName(),
                        source.getExpirationDate(),
                        source.getDescription(),
                        source.getWeight(),
                        source.getAmount(),
                        source.isFragile(),
                        source.getState(),
                        source.getMedicalPurpose()
                        );
                break;
        }
        return product;

    }
}
