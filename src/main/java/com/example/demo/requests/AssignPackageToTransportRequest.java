package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignPackageToTransportRequest {

    private Long deliveryId;
    private Long transportId;
    private Boolean finalDestination;
}
