package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode


public class Customer {

    private String customerld ;

    private String name;

    private String contact;

    private String email;

    private String address;

    private String nic;

    private String bailVehicleNm;


}
