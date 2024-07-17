package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String supplierId;

    private String name;

    private String contact;

    private String specialCar;

    private Double specialCarCost;

    private String vehicleId;


}
