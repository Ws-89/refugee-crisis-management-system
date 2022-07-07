package com.example.demo.dto;

import com.example.demo.models.products.Category;
import com.example.demo.models.products.State;
import com.example.demo.models.products.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}