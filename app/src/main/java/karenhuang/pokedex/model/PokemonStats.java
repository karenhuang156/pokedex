package karenhuang.pokedex.model;

/**
 * Created by karenhuang on 2/10/16.
 */

public class PokemonStats {
    private Stat stat;
    private int base_stat;

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public int getBaseStat() {
        return base_stat;
    }

    public void setBaseStat(int base_stat) {
        this.base_stat = base_stat;
    }
}
