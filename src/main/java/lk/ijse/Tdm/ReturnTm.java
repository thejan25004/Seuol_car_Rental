package lk.ijse.Tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnTm {
    private String returnId;
    private Double afterRideMilage;
    private Double lateReturnCharge;
    private Double TotalCost;
    private String customerld;
    private String reservationId;
}
