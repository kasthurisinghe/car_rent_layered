package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnEntity {
    private String bookingID;
    private LocalDate endDate;
    private String custId;
    private String custName;
    private String vehiRegnNo;
    private Integer charge;
}
