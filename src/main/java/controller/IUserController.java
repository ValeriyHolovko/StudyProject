package controller;

import model.Advertisement;

import java.util.ArrayList;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public interface IUserController {

    ArrayList<Advertisement> getAllAdvertisements();

    Advertisement findAdvertisementByID(int id);

    ArrayList<Advertisement> findAdvertisementByTitle(String title);

    ArrayList<Advertisement> getMyAdvertisements();

    void addAdvertisement(String title, String content);

    void deleteAdvertisement(int id);

}
