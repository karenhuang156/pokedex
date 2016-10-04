package karenhuang.pokedex.interfaces;


import karenhuang.pokedex.model.PokemonDetail;

import karenhuang.pokedex.model.PokemonSpecies;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by karenhuang on 14/09/16.
 * Client service for poke-api
 */

public interface PokeApi {


    @GET("pokemon")
    Call<ResponseBody> loadPokemon(@Query("limit") int limit);

    @GET("pokemon/{id}")
    Call<PokemonDetail>getPokemonDetail(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Call<PokemonSpecies>getPokemonSpecies(@Path("id") int id);



}
