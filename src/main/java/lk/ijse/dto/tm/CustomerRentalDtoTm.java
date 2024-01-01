package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRentalDtoTm {

    private String bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer rate;
    private String overDue;
    private Integer total;
    private String returned;

}
