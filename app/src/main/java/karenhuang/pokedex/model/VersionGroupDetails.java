package karenhuang.pokedex.model;

/**
 * Created by karenhuang on 3/10/16.
 */

public class VersionGroupDetails {
    private MoveLearnMethod move_learn_method;
    private int level_learned_at;

    public MoveLearnMethod getMove_learn_method() {
        return move_learn_method;
    }

    public void setMove_learn_method(MoveLearnMethod move_learn_method) {
        this.move_learn_method = move_learn_method;
    }

    public int getLevelLearnedAt() {
        return level_learned_at;
    }

    public void setLevelLearnedAt(int level_learned_at) {
        this.level_learned_at = level_learned_at;
    }

    class MoveLearnMethod {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
