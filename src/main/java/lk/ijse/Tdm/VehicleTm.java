package lk.ijse.Tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleTm {

    private String vehicleId ;
    private String modelName ;
    private Double bookingCost ;
    private String numberPlate ;
    private Double currentMilage ;
    private Double first_100Km_1km_charge ;
    private Double after_100Km_1km_charge ;
    private String userId ;


}
