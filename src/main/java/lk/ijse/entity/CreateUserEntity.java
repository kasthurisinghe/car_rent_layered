package lk.ijse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserEntity {
    private String id;
    private String name;
    private String addr;
    private  String mobile;
    private String pass;
    private String gender;

}
