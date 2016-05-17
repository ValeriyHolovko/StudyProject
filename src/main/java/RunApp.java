import controller.LoginController;
import model.Admin;
import model.AppDB;
import model.User;
import view.LoginFrame;

import java.math.BigInteger;

/**
 * Created by Valeriy Holovko on 16.05.2016.
 */
public class RunApp {
    public static void main(String[] args) {

        AppDB appDB = new AppDB();
        appDB.getUsers().add(new Admin("Vanya","123"));
        appDB.getUsers().add(new User("Val","123","Valeriy","Holovko",066));

        new LoginFrame(appDB,new LoginController(appDB));


    }
}
