package controller;

import model.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class AdminController implements IAdminController {

    AppDB appDB;

    public AdminController(AppDB appDB) {
        this.appDB = appDB;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<UserEntity> users = appDB.getUsers();
        ArrayList<User> forReturn = new ArrayList<User>();

        for (UserEntity user : users) {
            if (user instanceof User){
                forReturn.add((User) user);
            }
        }
        return forReturn;
    }

    @Override
    public ArrayList<Admin> getAllAdmins() {
        ArrayList<UserEntity> users = appDB.getUsers();
        ArrayList<Admin> forReturn = new ArrayList<Admin>();

        for (UserEntity user : users) {
            if (user instanceof Admin){
                forReturn.add((Admin) user);
            }
        }
        return forReturn;
    }

    @Override
    public UserEntity getUserByID(int id) throws NoSuchElementException{
        ArrayList<UserEntity> users = appDB.getUsers();
        for (UserEntity user : users) {
            if (user.getId() == id){
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public UserEntity getUserByLogin(String login) throws NoSuchElementException {
        ArrayList<UserEntity> users = appDB.getUsers();
        for (UserEntity user : users) {
            if (user.getLogin().equals(login)){
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void banUser(int id) throws NoSuchElementException {
        ArrayList<UserEntity> users = appDB.getUsers();
        for (UserEntity user : users) {
            if (user.getId() == id && user instanceof User){
                ((User) user).setAccess(false);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public ArrayList<Advertisement> getAllAdvertisements() {
        return appDB.getAdvertisements();
    }

    @Override
    public Advertisement getAdvertisementByID(int id) throws NoSuchElementException{
        ArrayList<Advertisement> advertisements = appDB.getAdvertisements();
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getId() == id){
                return advertisement;
            }
        }
        throw new NoSuchElementException("No advertisement with such id.");
    }

    @Override
    public ArrayList<Advertisement> getAdvertisementByTitle(String title) {
        ArrayList<Advertisement> advertisements = appDB.getAdvertisements();
        ArrayList<Advertisement> forReturn = new ArrayList<Advertisement>();

        for (Advertisement advertisement : advertisements) {
            if (advertisement.getTitle().equals(title)){
                forReturn.add(advertisement);
            }
        }
        return forReturn;
    }

    @Override
    public void deleteAdvertisement(int id) {
        Advertisement advertisement = getAdvertisementByID(id);

        appDB.getAdvertisements().remove(advertisement);
    }
}
