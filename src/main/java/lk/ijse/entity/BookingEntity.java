package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingEntity {
    private String carId;
    private Integer rate;
    private String custId;
    private LocalDate endDat;
    private LocalDate startDat;
    private String bookingId;
    private Integer total;
    private Boolean isReturned;
}
