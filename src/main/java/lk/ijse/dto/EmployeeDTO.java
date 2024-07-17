package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String EmployeeId;
    private String name;
    private String employeeType;
    private Double salary;
    private String  userId;
}
