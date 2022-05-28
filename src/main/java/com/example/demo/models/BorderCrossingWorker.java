package com.example.demo.models;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import com.example.demo.models.shared.WorkerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_border_crossing_worker")
public class BorderCrossingWorker {

    @Id
    @SequenceGenerator(
            name = "border_crossing_worker_strategy",
            sequenceName = "border_crossing_worker_strategy",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "border_crossing_worker_strategy"
    )
    private long id;
    @Embedded
    private Person person;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    @Embedded
    private WorkerDetails workerDetails;
}
