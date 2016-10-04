package karenhuang.pokedex.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karenhuang on 27/9/16.
 * Model class for detailed information for each Pokemon
 */

public class PokemonDetail {
    private List<PokemonStats> stats = new ArrayList<>();
    private List<PokemonType> types = new ArrayList<>();
    private List<PokemonAbility> abilities = new ArrayList<>();
    private PokemonSpecies species = new PokemonSpecies();
    private List<PokemonMoves> moves = new ArrayList<>();

    private int id;
    private String name;
    private int base_experience;
    private int height;
    private int weight;

    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int spAtk;
    private int spDef;

    private String type1;
    private String type2;
    private String pAbilities;
    private String description;
    private int captureRate;
    private int baseHappiness;
    private String moveList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBaseExp() {
        return base_experience;
    }

    public void setBaseExp(int baseExp) {
        this.base_experience = baseExp;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<PokemonStats> getPokemonStats() {
        return stats;
    }

    public void setPokemonStats(List<PokemonStats> stats) {
        this.stats = stats;
    }

    public List<PokemonType> getPokemonType() {
        return types;
    }

    public void setPokemonType(List<PokemonType> types) {
        this.types = types;
    }

    public List<PokemonAbility> getPokemonAbilities() {
        return abilities;
    }

    public void setPokemonAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getpAbilities() {
        return pAbilities;
    }

    public void setpAbilities(String pAbilities) {
        this.pAbilities = pAbilities;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecies species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public int getBaseHappiness() {
        return baseHappiness;
    }

    public void setBaseHappiness(int baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public List<PokemonMoves> getPokemonMoves() {
        return moves;
    }

    public void setPokemonMoves(List<PokemonMoves> moves) {
        this.moves = moves;
    }

    public String getMoveList() {
        return moveList;
    }

    public void setMoveList(String moveList) {
        this.moveList = moveList;
    }
}



