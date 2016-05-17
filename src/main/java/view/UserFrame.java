package view;

import controller.IAdminController;
import controller.IUserController;
import model.Admin;
import model.User;

import javax.swing.*;

/**
 * Created by Valeriy Holovko on 16.05.2016.
 */
public class UserFrame extends JFrame{

    private Admin user;
    private IAdminController adminController;

    public UserFrame(User user, IUserController userController) {
    }
}
