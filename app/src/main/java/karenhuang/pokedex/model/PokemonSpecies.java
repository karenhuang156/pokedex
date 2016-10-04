package karenhuang.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karenhuang on 3/10/16.
 */

public class PokemonSpecies {
    private int capture_rate;
    private int base_happiness;
    private List<FlavourText> flavor_text_entries = new ArrayList<>();

    public int getCapture_rate() {
        return capture_rate;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public int getBase_happiness() {
        return base_happiness;
    }

    public void setBase_happiness(int base_happiness) {
        this.base_happiness = base_happiness;
    }

    public List<FlavourText> getFlavourTextList() {
        return flavor_text_entries;
    }

    public void setFlavourTextList(List<FlavourText> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }
}
