package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDtoTm {
    private String custId;
    private String custName;
    private String custAddress;
    private String custNic;
    private String custMobile;
    private String custGender;
}
