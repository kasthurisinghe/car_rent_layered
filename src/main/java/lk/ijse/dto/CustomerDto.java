package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String cusId;
    private String cusName;
    private String cusAdd;
    private String cusNic;
    private String cusmobile;
    private String gender;
}
