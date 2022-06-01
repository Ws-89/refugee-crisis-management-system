package com.example.demo.models.productsdelivery;

import com.example.demo.models.GenericEntity;
import com.example.demo.models.shared.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@Table(name = "tbl_product_delivery")
@AllArgsConstructor
@ToString
public class ProductDelivery implements Serializable, GenericEntity<ProductDelivery> {

    @Id
    @SequenceGenerator(
            name = "product_delivery_sequence",
            sequenceName = "product_delivery_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_delivery_sequence"
    )
    private long deliveryId;
    private String description;
    @Type(type = "com.example.demo.models.productsdelivery.ProductDeliveryStateType")
    @Column(name = "delivery_state")
    private ProductDeliveryState state;
    private Double capacity;
    private Address startingPoint;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "delivery_id",
            referencedColumnName = "deliveryId"
    )
    private List<DeliveryStatusMessage> messageLog = new ArrayList<>();

    public ProductDelivery() {
        this.state = new ProductDeliveryStateStarted();
        this.messageLog.add(new DeliveryStatusMessage("New delivery has been created", LocalDateTime.now()));
    }

    public String getStateName(){
        return this.state.getName();
    }

    public void nextState(){
        state.nextState(this);
        this.messageLog.add(new DeliveryStatusMessage());
    }

    public void prevState(){
        state.prevState(this);
    }


    @Override
    public void update(ProductDelivery source) {
        this.state = source.getState();
        this.description = source.getDescription();
    }

    @Override
    public Long getId() {
        return this.getDeliveryId();
    }

    @Override
    public ProductDelivery createNewInstance() {
        ProductDelivery newInstance = new ProductDelivery();
        newInstance.update(this);
        return newInstance;
    }
}
