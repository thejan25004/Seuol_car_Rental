package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class ReturnDetails {
        private String returnId;
        private String afterRideMilage;
        private String returnCharge;
        private Double TotalCost;
        private String customerId;
        private String reservationId;
        private String vehicleId;


}
