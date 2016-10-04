package karenhuang.pokedex.model;

/**
 * Created by karenhuang on 2/10/16.
 */

public class Stat {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        name = name.toUpperCase();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
