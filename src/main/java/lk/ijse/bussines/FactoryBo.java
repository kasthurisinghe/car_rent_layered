package lk.ijse.bussines;

import lk.ijse.bussines.custom.CreateUserBo;
import lk.ijse.bussines.custom.impl.CreateUserBoImpl;

public class FactoryBo {

    public static <T> T  getBo(BoType boType) {
        switch (boType){
            case ADMINUSER:{
                return (T)new CreateUserBoImpl();
            }
            default : {
                return null;
            }
        }
    }
}
