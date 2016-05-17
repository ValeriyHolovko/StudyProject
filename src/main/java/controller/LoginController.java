package controller;

import model.AppDB;
import model.User;
import model.UserEntity;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class LoginController implements ILoginController {

    AppDB appDB;

    public LoginController(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public UserEntity login(String login, String password) throws NoSuchElementException {

        ArrayList<UserEntity> users = appDB.getUsers();
        for (UserEntity user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }

        throw new NoSuchElementException("Wrong login or password.");
    }

    @Override
    public void register(String login, String password, String name, String surname, long phone) throws IllegalArgumentException{

        ArrayList<UserEntity> users = appDB.getUsers();
        for (UserEntity user : users) {
            if (user.getLogin().equals(login)){
                throw new IllegalArgumentException("User with such login already exists.");
            }
        }

        User user = new User(login, password, name, surname, phone);
        appDB.getUsers().add(user); //users.add(user); if reference is linked to the same object
    }
}
