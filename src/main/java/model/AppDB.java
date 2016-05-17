package model;

import java.util.ArrayList;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class AppDB {

    ArrayList<UserEntity> users = new ArrayList<>();
    ArrayList<Advertisement> advertisements = new ArrayList<>();

    public ArrayList<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserEntity> users) {
        this.users = users;
    }

    public ArrayList<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(ArrayList<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
