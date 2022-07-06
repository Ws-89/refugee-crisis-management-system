package com.example.demo.models.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    String categoryName;
    String attr1Caption;
    String attr2Caption;
    String attr3Caption;
}
