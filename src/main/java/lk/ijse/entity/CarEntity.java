package lk.ijse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarEntity {
    private String regNo;
    private String idNo;
    private String modle;
    private String barnd;
    private String colour;
    private String type;

}
