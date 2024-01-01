package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllOverDueEntityTm {
    private String custId;
    private String vehicleId;
    private LocalDate endDate;
    private Integer rate;
    private Integer amount;
    private Boolean returned;
}
