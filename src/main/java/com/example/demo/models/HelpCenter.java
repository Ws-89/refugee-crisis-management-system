package com.example.demo.models;



import com.example.demo.models.materialResources.MaterialResource;
import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "tbl_help_center")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelpCenter {

    @Id
    @SequenceGenerator(
            name = "help_center_sequence",
            sequenceName = "help_center_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "help_center_sequence"
    )
    private long id;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "help_center_id",
            referencedColumnName = "id"
    )
    Collection<MaterialResource> materials = new ArrayList<MaterialResource>();


    public void addToMaterials(MaterialResource resource){
        if(resource == null)
            throw new IllegalArgumentException("Resource cannot be null");
        this.materials.add(resource);
    }
}
