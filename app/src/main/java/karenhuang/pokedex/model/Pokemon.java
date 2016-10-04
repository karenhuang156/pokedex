package karenhuang.pokedex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karenhuang on 14/09/16.
 * Model class for Pokemon
 */
public class Pokemon {

    private String url;
    private int id;
    private String name;

    public Pokemon(String name, String url){
        this.url = url;
        this.name = name;
    }

    public String getName() {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url =url;
    }

    public int getId() {
        String segments[] = getUrl().split("/");
        String intStr = segments[segments.length - 1];
        id = Integer.parseInt(intStr);
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " Pokemon name = " + name + ", url = " + url;
    }



}
