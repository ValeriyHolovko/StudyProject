package model;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public class Advertisement extends IDEntity{

    private String title;
    private String content;
    private User advertiser;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(User advertiser) {
        this.advertiser = advertiser;
    }

    public Advertisement(String title, String content, User advertiser) {
        this.title = title;

        this.content = content;
        this.advertiser = advertiser;
    }
}
