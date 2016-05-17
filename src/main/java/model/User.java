package model;

import java.util.ArrayList;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class User extends UserEntity {

    private String name;
    private String surname;
    private long phone;
    private boolean access;
    private ArrayList<Advertisement> advertisements;//no need to input in constructor as it will be empty anyway

    public User(String login, String password, String name, String surname, long phone) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        access = true;
        advertisements = new ArrayList<>();
    }

    public boolean isAccess() {
        return access;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", access=" + access +
                ", advertisements=" + advertisements +
                '}';
    }


    public void setAccess(boolean access) {
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public ArrayList<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(ArrayList<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
