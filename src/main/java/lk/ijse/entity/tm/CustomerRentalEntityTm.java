package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRentalEntityTm {

    private String bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer rate;
    private Integer total;
    private Boolean returned;
}
