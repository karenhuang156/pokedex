package karenhuang.pokedex.adapters;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import karenhuang.pokedex.PokemonDisplayActivity;
import karenhuang.pokedex.R;
import karenhuang.pokedex.interfaces.ItemClickListener;
import karenhuang.pokedex.model.Pokemon;

/**
 * Created by karenhuang on 14/09/16.
 * Adapter to display pokemon
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>implements Filterable {

    ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
    ArrayList<Pokemon> filterList;
    Context context;
    CustomFilter filter;
    public static int selectedPokemon;



    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemonArrayList) {
        this.context = context;
        this.pokemonArrayList = pokemonArrayList;
        this.filterList = pokemonArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Bind Data
        Pokemon p = pokemonArrayList.get(position);
        holder.name_display.setText(p.getName());
        holder.id_display.setText(String.valueOf(p.getId()));

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getId() + ".png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .thumbnail(0.5f)
                .crossFade()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        //Item Click
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Snackbar.make(v,pokemonArrayList.get(position).getName(),Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    //return filter object
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter(filterList,this);

        }
        return filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView name_display;
        private TextView id_display;
        ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            name_display = (TextView) itemView.findViewById(R.id.name_display);
            id_display = (TextView) itemView.findViewById(R.id.id_display);

            itemView.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, this.getLayoutPosition());
            int position = getAdapterPosition();
            selectedPokemon = position + 1;
            System.out.println("select = " + selectedPokemon);

            Intent intent = new Intent(context, PokemonDisplayActivity.class);
            view.getContext().startActivity(intent);
        }
    }

}
