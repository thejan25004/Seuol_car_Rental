package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnDTO {
    private String returnId;
    private Double afterRideMilage;
    private Double lateReturnCharge;
    private Double TotalCost;
    private String customerld;
    private String reservationId;
}
