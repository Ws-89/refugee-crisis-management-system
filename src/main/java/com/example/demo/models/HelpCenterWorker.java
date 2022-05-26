package com.example.demo.models;

import com.example.demo.models.shared.Address;
import com.example.demo.models.shared.Contact;
import com.example.demo.models.shared.Person;
import com.example.demo.models.shared.WorkerDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_help_center_worker")
public class HelpCenterWorker {
    @Id
    @SequenceGenerator(
            name = "help_center_worker_sequence",
            sequenceName = "help_center_worker_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "help_center_worker_sequence"
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
