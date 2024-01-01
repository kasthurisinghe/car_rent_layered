package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllOverDueDtoTm {
    private String custId;
    private String vehicleId;
    private LocalDate dueDate;
    private Integer rate;
    private Integer totalCharge;
}
