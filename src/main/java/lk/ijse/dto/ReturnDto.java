package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnDto {
    private String bookingId;
    private String custId;
    private String isOverDue;
    private String custName;
    private String vehiRegNo;
    private Integer panelty;
    private Integer total;
}
