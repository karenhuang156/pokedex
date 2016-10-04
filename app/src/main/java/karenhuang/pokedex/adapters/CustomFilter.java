package karenhuang.pokedex.adapters;

import android.widget.Filter;
import java.util.ArrayList;
import karenhuang.pokedex.adapters.PokemonAdapter;
import karenhuang.pokedex.model.Pokemon;

/**
 * Created by karenhuang on 3/10/16.
 * This class performs the filtering on pokemon list in main activity.
 * Reference - https://www.youtube.com/watch?v=XtEtsMoOLog
 */

public class CustomFilter extends Filter {
    private PokemonAdapter pokemonAdapter;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> filterList;

    public CustomFilter(ArrayList<Pokemon> filterList, PokemonAdapter pokemonAdapter){
        this.filterList = filterList;
        this.pokemonAdapter = pokemonAdapter;

    }
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //check constraints
        if(charSequence != null && charSequence.length()>0) {
            //Change to uppercase
            charSequence = charSequence.toString().toUpperCase();
            //Store filtered results
            ArrayList<Pokemon> filteredPokemon = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                //check
                if (filterList.get(i).getName().toUpperCase().contains(charSequence)) {
                    filteredPokemon.add(filterList.get(i));

                }
            }

            results.count = filteredPokemon.size();
            results.values = filteredPokemon;
        }else{

            results.count = filterList.size();
            results.values = filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        pokemonAdapter.pokemonArrayList = (ArrayList<Pokemon>)filterResults.values;
        pokemonAdapter.notifyDataSetChanged();
    }
}
