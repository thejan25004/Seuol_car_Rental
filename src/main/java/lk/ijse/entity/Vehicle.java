package lk.ijse.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {

    private String vehicleId ;
    private String modelName ;
    private Double bookingCost ;
    private String numberPlate ;
    private Double currentMilage ;
    private Double first_100Km_1km_charge ;
    private Double after_100Km_1km_charge ;
    private String userId ;



}
