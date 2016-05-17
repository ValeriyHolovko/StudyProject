package controller;

import model.UserEntity;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public interface ILoginController {

    UserEntity login(String login, String password);

    void register(String login, String password, String name, String surname, long phone);

}
