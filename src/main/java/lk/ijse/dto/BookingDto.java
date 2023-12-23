package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
    private String bookingId;
    private String carId;
    private Integer rate;
    private String custId;
    private LocalDate endDat;
    private LocalDate startDat;
    Boolean isReturned;
}
