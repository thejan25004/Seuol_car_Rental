package lk.ijse.Tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationTm {
    private String reservationId;
    private Date reservationDate;
    private Date returnDate;
    private String customerld;
}
