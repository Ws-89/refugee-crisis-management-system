package com.example.demo.models.products;

import com.example.demo.models.cargo.Cargo;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
@Builder
@Entity
public class Product implements Serializable {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "product_id")
    private Long productId;
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String name;
    private LocalDate expirationDate;
    private String description;
    @NotNull
    private double weight;
    @NotNull
    private Long amount;
    @Enumerated(EnumType.STRING)
    private Status reserved;
    private boolean fragile;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @NotNull
    @Embedded
    private Category category;
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id")
    private Cargo cargo;

//    @JsonBackReference
    public Cargo getCargo() {
        return cargo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getReserved() {
        return reserved;
    }

    public void setReserved(Status reserved) {
        this.reserved = reserved;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", expirationDate=" + expirationDate +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", amount=" + amount +
                ", reserved=" + reserved +
                ", fragile=" + fragile +
                ", state=" + state +
                ", productDelivery=" + cargo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.weight, weight) == 0 && fragile == product.fragile && Objects.equals(productId, product.productId) && Objects.equals(name, product.name) && Objects.equals(expirationDate, product.expirationDate) && Objects.equals(description, product.description) && Objects.equals(amount, product.amount) && reserved == product.reserved && state == product.state && Objects.equals(category, product.category) && Objects.equals(cargo, product.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, expirationDate, description, weight, amount, reserved, fragile, state, category, cargo);
    }
}
