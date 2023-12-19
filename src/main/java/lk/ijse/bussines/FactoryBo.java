package lk.ijse.bussines;


import lk.ijse.bussines.custom.impl.CreateUserBoImpl;
import lk.ijse.bussines.custom.impl.LoginBoImpl;

public class FactoryBo {

    public static <T> T  getBo(BoType boType) {
        switch (boType){
            case ADMINUSER:
                return (T)new CreateUserBoImpl();

            case LOGINBO:
                return (T) new LoginBoImpl();
            default :
                return null;

        }
    }
}
