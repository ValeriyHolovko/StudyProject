package controller;

import model.Advertisement;
import model.AppDB;
import model.User;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class UserController implements IUserController {

    AppDB appDB;
    User user;

    public UserController(AppDB appDB, User user) {
        this.appDB = appDB;
        this.user = user;
    }

    @Override
    public ArrayList<Advertisement> getAllAdvertisements() {
        return appDB.getAdvertisements();
    }

    @Override
    public Advertisement findAdvertisementByID(int id) throws NoSuchElementException {
        ArrayList<Advertisement> advertisements = appDB.getAdvertisements();
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getId() == id){
                return advertisement;
            }
        }

        throw new NoSuchElementException("No advertisement with such id.");
    }

    @Override
    public ArrayList<Advertisement> findAdvertisementByTitle(String title) {
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
    public ArrayList<Advertisement> getMyAdvertisements() {
        return user.getAdvertisements();
    }

    @Override
    public void addAdvertisement(String title, String content) {
        Advertisement advertisement = new Advertisement(title,content,user);
        appDB.getAdvertisements().add(advertisement);
    }

    @Override
    public void deleteAdvertisement(int id) throws AccessControlException{
        Advertisement advertisement = findAdvertisementByID(id);
        if (!advertisement.getAdvertiser().equals(user)){
            throw new AccessControlException("Access is forbidden. Advertisement is not yours.");
        }
        appDB.getAdvertisements().remove(advertisement);
    }
}
