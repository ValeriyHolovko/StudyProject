package model;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public abstract class UserEntity extends IDEntity{

    private String login;
    private String password;

    protected UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
