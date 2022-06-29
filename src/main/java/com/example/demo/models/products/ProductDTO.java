package com.example.demo.models.products;

import com.example.demo.models.productsdelivery.ProductDelivery;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private LocalDateTime expirationDate;
    private String description;
    private double weight;
    private Long amount;
    private Status reserved;
    private boolean fragile;
    private State state;
    private Category category;

    public static ProductDTO createDTOfromProduct(Product source){
        Category category = Category.builder()
                .categoryName(source.getCategory().getCategoryName())
                .attr1Caption(source.getCategory().getAttr1Caption())
                .attr2Caption(source.getCategory().getAttr2Caption())
                .attr3Caption(source.getCategory().getAttr3Caption())
                .build();

        return ProductDTO.builder()
                .productId(source.getProductId())
                .name(source.getName())
                .description(source.getDescription())
                .expirationDate(source.getExpirationDate())
                .weight(source.getWeight())
                .amount(source.getAmount())
                .reserved(source.getReserved())
                .fragile(source.isFragile())
                .state(source.getState())
                .category(category)
                .build();
    }
}
