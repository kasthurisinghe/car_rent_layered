package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity {
    private String cusId;
    private String cusName;
    private String cusAdd;
    private String cusNic;
    private String cusmobile;
    private String gender;
}
