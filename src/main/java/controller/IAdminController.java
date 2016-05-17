package controller;

import model.Admin;
import model.Advertisement;
import model.User;
import model.UserEntity;

import java.util.ArrayList;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public interface IAdminController {

    ArrayList<User> getAllUsers();

    ArrayList<Admin> getAllAdmins();

    UserEntity getUserByID(int id);

    UserEntity getUserByLogin(String login);

    void banUser(int id);

    ArrayList<Advertisement> getAllAdvertisements();

    Advertisement getAdvertisementByID(int id);

    ArrayList<Advertisement> getAdvertisementByTitle(String title);

    void deleteAdvertisement(int id);

}

