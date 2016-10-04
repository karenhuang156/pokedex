package karenhuang.pokedex.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.List;

import karenhuang.pokedex.R;
import karenhuang.pokedex.model.Pokemon;
import karenhuang.pokedex.model.PokemonDetail;
import karenhuang.pokedex.model.PokemonSpecies;

/**
 * Created by karenhuang on 2/10/16.
 * Adapter for displaying pokemon detail
 */

public class PokemonDetailAdapter extends RecyclerView.Adapter<PokemonDetailAdapter.ViewHolder> {
    private List<PokemonDetail> pList;

    private Context context;

    public PokemonDetailAdapter(Context context, List<PokemonDetail> pList) {
        this.pList = pList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_detail_item, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokemonDetail pokemonDetail = pList.get(position);
        holder.pokemonName.setText(pokemonDetail.getName());
            System.out.println(pokemonDetail.getName());
        holder.pokemonID.setText("#" + String.valueOf(pokemonDetail.getId()));
            System.out.println(pokemonDetail.getId());

        //load gif
        Glide.with(context)
                .load("http://www.smogon.com/dex/media/sprites/xy/"+pokemonDetail.getName().toLowerCase() +".gif")
                //.load("http://pokeapi.co/media/sprites/pokemon/" + pokemonDetail.getId() + ".png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .thumbnail(0.5f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.spriteDisplay);



        holder.hp.setText(String.valueOf(pokemonDetail.getHp()));
        holder.HpProgressBar.setProgress(pokemonDetail.getHp());
        holder.attack.setText(String.valueOf(pokemonDetail.getAttack()));
        holder.attackProgressBar.setProgress(pokemonDetail.getAttack());
        holder.defense.setText(String.valueOf(pokemonDetail.getDefense()));
        holder.defenseProgressBar.setProgress(pokemonDetail.getDefense());
        holder.speed.setText(String.valueOf(pokemonDetail.getSpeed()));
        holder.speedProgressBar.setProgress(pokemonDetail.getSpeed());
        holder.spAtk.setText(String.valueOf(pokemonDetail.getSpAtk()));
        holder.spAtkProgressBar.setProgress(pokemonDetail.getSpAtk());
        holder.spDef.setText(String.valueOf(pokemonDetail.getSpDef()));
        holder.spDefProgressBar.setProgress(pokemonDetail.getSpDef());

        holder.type1.setText(pokemonDetail.getType1());
        /*switch (pokemonDetail.getType1()) {
            case "Poison":
                holder.type1.setTextColor(Color.parseColor("#A040A0"));
            case "Grass":
                holder.type1.setTextColor(Color.parseColor("#78C850"));
            case "Flying":
                holder.type1.setTextColor(Color.parseColor("#A890F0"));
            case "Fire":
                holder.type1.setTextColor(Color.parseColor("#F08030"));
            case "Ghost":
                holder.type1.setTextColor(Color.parseColor("#705898"));
            case "Bug":
                holder.type1.setTextColor(Color.parseColor("#A8B820"));
            case "Normal":
                holder.type1.setTextColor(Color.parseColor("#A8A878"));
            case "Fairy":
                holder.type1.setTextColor(Color.parseColor("#EE99AC"));
            case "Dragon":
                holder.type1.setTextColor(Color.parseColor("#7038F8"));
            case "Ice":
                holder.type1.setTextColor(Color.parseColor("#98D8D8"));
            case "Fighting":
                holder.type1.setTextColor(Color.parseColor("#C03028"));
            case "Ground":
                holder.type1.setTextColor(Color.parseColor("#E0C068"));
            case "Electric":
                holder.type1.setTextColor(Color.parseColor("#F8D030"));
            case "Rock":
                holder.type1.setTextColor(Color.parseColor("#B8A038"));
            case "Psychic":
                holder.type1.setTextColor(Color.parseColor("#F85888"));
            case "Water":
                holder.type1.setTextColor(Color.parseColor("#6890F0"));
            case "Dark":
                holder.type1.setTextColor(Color.parseColor("#705848"));
            case "Steel":
                holder.type1.setTextColor(Color.parseColor("#B8B8D0"));

        }*/
        holder.type2.setText(pokemonDetail.getType2());
        holder.height.setText(String.valueOf(pokemonDetail.getHeight()/10.0) + " m");
        holder.weight.setText(String.valueOf(pokemonDetail.getWeight()/10.0)+ " kg");
        holder.abilities.setText(pokemonDetail.getpAbilities());

        //
        System.out.println("adapter" + pokemonDetail.getDescription());
        holder.pokemonDescDetail.setText(pokemonDetail.getDescription());
        holder.capture.setText(String.valueOf(pokemonDetail.getCaptureRate()));
        holder.baseExp.setText(String.valueOf(pokemonDetail.getBaseExp()));
        holder.baseHap.setText(String.valueOf(pokemonDetail.getBaseHappiness()));
        holder.moves.setText(pokemonDetail.getMoveList());


    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView spriteDisplay;
        private TextView pokemonName;
        private TextView pokemonID;
        //
        private TextView hp;
        private ProgressBar HpProgressBar;
        private TextView attack;
        private ProgressBar attackProgressBar;
        private TextView defense;
        private ProgressBar defenseProgressBar;
        private TextView speed;
        private ProgressBar speedProgressBar;
        private TextView spAtk;
        private ProgressBar spAtkProgressBar;
        private TextView spDef;
        private ProgressBar spDefProgressBar;
        //
        private TextView pokemonDescTitle;
        private TextView pokemonDescDetail;
        //
        private TextView type1;
        private TextView type2;
        private TextView height;
        private TextView weight;
        private TextView abilities;
        private TextView capture;
        private TextView baseExp;
        private TextView baseHap;
        private TextView moves;




        public ViewHolder(View view) {
            super(view);

            pokemonName = (TextView) view.findViewById(R.id.pokemonName);
            pokemonID = (TextView) view.findViewById(R.id.pokemonId);
            spriteDisplay = (ImageView) view.findViewById(R.id.spriteDisplay);
            pokemonDescTitle = (TextView) view.findViewById(R.id.pokemonDescTitle);

            hp = (TextView) view.findViewById(R.id.hp);
            HpProgressBar = (ProgressBar) view.findViewById(R.id.HpProgressBar);
            attack = (TextView) view.findViewById(R.id.attack);
            attackProgressBar = (ProgressBar) view.findViewById((R.id.attackProgressBar));
            defense = (TextView) view.findViewById(R.id.defense);
            defenseProgressBar= (ProgressBar) view.findViewById((R.id.defenseProgressBar));
            speed = (TextView) view.findViewById(R.id.speed);
            speedProgressBar= (ProgressBar) view.findViewById((R.id.speedProgressBar));
            spAtk = (TextView) view.findViewById(R.id.spAtk);
            spAtkProgressBar= (ProgressBar) view.findViewById((R.id.spAtkProgressBar));
            spDef = (TextView) view.findViewById(R.id.spDef);
            spDefProgressBar= (ProgressBar) view.findViewById((R.id.spDefProgressBar));

            type1 = (TextView) view.findViewById(R.id.type1);
            type2 = (TextView) view.findViewById(R.id.type2);
            height = (TextView) view.findViewById(R.id.height);
            weight = (TextView) view.findViewById(R.id.weight);
            abilities = (TextView) view.findViewById(R.id.abilities);
            pokemonDescDetail = (TextView) view.findViewById(R.id.pokemonDescDetail);
            capture = (TextView) view.findViewById(R.id.capture);
            baseExp = (TextView) view.findViewById(R.id.baseExp);
            baseHap = (TextView) view.findViewById(R.id.baseHap);
            moves = (TextView) view.findViewById(R.id.moves);


        }


    }
}
