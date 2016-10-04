package karenhuang.pokedex.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karenhuang on 3/10/16.
 */

public class PokemonMoves {
    private List<VersionGroupDetails> version_group_details = new ArrayList<>();
    private Move move;

    public List<VersionGroupDetails> getVersion_group_details() {
        return version_group_details;
    }

    public void setVersion_group_details(List<VersionGroupDetails> version_group_details) {
        this.version_group_details = version_group_details;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
