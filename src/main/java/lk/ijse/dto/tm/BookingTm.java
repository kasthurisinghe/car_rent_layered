package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingTm {
    private String bookingId;
    private String carId;
    private String custId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer rate;
    private Integer total;

}
