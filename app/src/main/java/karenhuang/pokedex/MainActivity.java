package karenhuang.pokedex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import karenhuang.pokedex.adapters.PokemonAdapter;
import karenhuang.pokedex.model.Pokemon;
import karenhuang.pokedex.interfaces.PokeApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karenhuang.
 * This class performs the filtering on pokemon list in main activity.
 * Reference - https://www.youtube.com/watch?v=XtEtsMoOLog
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity ";
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private Retrofit retrofit;
    private ProgressBar spinner;
    private SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        //ProgressBar
        spinner = (ProgressBar) findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);

        //Create retrofit client manager
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //make api call to load Pokemon
        loadData();

        spinner.setVisibility(View.GONE);

        //search
        sv = (SearchView) findViewById(R.id.searchView);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("Search Name");


    }

    public void loadData() {
        PokeApi service = retrofit.create(PokeApi.class);
        //load first 151 pokemon
        Call<ResponseBody> request = service.loadPokemon(151);

        System.out.println("call initiated");
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    String responsebody = "";
                    JsonArray objArray = null;

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    try {
                        responsebody = response.body().string();
                    } catch (IOException e) {
                        System.out.println("IOException");
                    }
                    JsonObject obj = gson.fromJson(responsebody, JsonObject.class);

                    objArray = obj.getAsJsonArray("results");

                    Pokemon[] pokemonList = gson.fromJson(objArray, Pokemon[].class);

                    ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
                    //add api response to arraylist
                    for (Pokemon pokemon : pokemonList) {
                        pokemonArrayList.add(pokemon);

                    }

                    //create pokemon adapter
                    adapter = new PokemonAdapter(getApplicationContext(), pokemonArrayList);

                    //handle search in searchView
                    sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            //Filter as user inputs text
                            adapter.getFilter().filter(newText);
                            return false;
                        }
                    });

                    //set adapter for recyclerView
                    recyclerView.setAdapter(adapter);


                } else {
                    Log.d(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Please restart application", Toast.LENGTH_LONG);
            }
        });

    }
}



