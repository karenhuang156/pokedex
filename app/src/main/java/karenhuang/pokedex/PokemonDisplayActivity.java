package karenhuang.pokedex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import karenhuang.pokedex.adapters.PokemonAdapter;

import karenhuang.pokedex.adapters.PokemonDetailAdapter;
import karenhuang.pokedex.interfaces.PokeApi;
import karenhuang.pokedex.model.PokemonDetail;


import karenhuang.pokedex.model.PokemonSpecies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokemonDisplayActivity extends AppCompatActivity {
    Retrofit retrofit;
    private RecyclerView recyclerView;
    private PokemonDetailAdapter detailAdapter;
    private List<PokemonDetail> pList;
    private PokemonDetail p;
    private PokemonSpecies pSpecies;
    private ProgressBar spinner;


    private static final String TAG = "PokemonDisplayActivity ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_display);


        //Initialise RecyclerView

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        spinner = (ProgressBar) findViewById(R.id.spinner);

        //Create retrofit client manager
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //Download data from API

        loadPokemonDetail();


    }

    public void loadPokemonDetail() {
        spinner.setVisibility(View.VISIBLE);
        PokeApi service = retrofit.create(PokeApi.class);
        Call<PokemonDetail> request = service.getPokemonDetail(PokemonAdapter.selectedPokemon);
        request.enqueue(new Callback<PokemonDetail>() {
                            @Override
                            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {

                                if (response.isSuccessful()) {
                                    try {
                                        p = response.body();
                                        //set Species in PokemonDetail to output of asynchronous call
                                        p.setSpecies(loadPokemonSpecies());

                                        //add callback response to an arrayList
                                        pList = new ArrayList<PokemonDetail>();
                                        pList.add(p);

                                        //retrieve pokemon types
                                        for (int i = 0; i < p.getPokemonType().size(); i++) {
                                            if (i == 0) {
                                                String type2 = p.getPokemonType().get(0).getType().getName();
                                                p.setType2(type2);
                                            } else if (i == 1) {
                                                String type1 = p.getPokemonType().get(1).getType().getName();
                                                p.setType1(type1);
                                            }

                                        }
                                        //retrieve pokemon statistics
                                        for (int i = 0; i < p.getPokemonStats().size(); i++) {
                                            int speed = p.getPokemonStats().get(0).getBaseStat();
                                            p.setSpeed(speed);
                                            int spDef = p.getPokemonStats().get(1).getBaseStat();
                                            p.setSpDef(spDef);
                                            int spAtk = p.getPokemonStats().get(2).getBaseStat();
                                            p.setSpAtk(spAtk);
                                            int defense = p.getPokemonStats().get(3).getBaseStat();
                                            p.setDefense(defense);
                                            int attack = p.getPokemonStats().get(4).getBaseStat();
                                            p.setAttack(attack);
                                            int hp = p.getPokemonStats().get(5).getBaseStat();
                                            p.setHp(hp);


                                        }
                                        //retrieve pokemon abilities
                                        if (p.getPokemonAbilities().size() >= 0) {
                                            String abilityList = p.getPokemonAbilities().get(0).getAbility().getName();
                                            for (int i = 1; i < p.getPokemonAbilities().size(); i++) {
                                                abilityList += ", " + p.getPokemonAbilities().get(i).getAbility().getName();
                                                p.setpAbilities(abilityList);
                                            }
                                        }

                                        //retrieve pokemon moves
                                        if (p.getPokemonMoves().size() >= 0) {
                                            String moveList = p.getPokemonMoves().get(0).getMove().getName();
                                            for (int i = 1; i < p.getPokemonMoves().size(); i++) {

                                                moveList += ", " + p.getPokemonMoves().get(i).getMove().getName();
                                                p.setMoveList(moveList);

                                                /*
                                                // retrieve pokemonLevels
                                                if (p.getPokemonMoves().get(i).getVersion_group_details().size() > 1) {
                                                    int level = p.getPokemonMoves().get(i).getVersion_group_details().get(1).getLevelLearnedAt();
                                                    System.out.println(level);
                                                } else {
                                                    System.out.println("error");
                                                }*/
                                            }

                                        }


                                    } catch (Exception e) {
                                        Log.d("onResponse", "error !!");
                                        e.printStackTrace();
                                    }

                                    //set detailAdapter
                                    detailAdapter = new PokemonDetailAdapter(PokemonDisplayActivity.this, pList);
                                    recyclerView.setAdapter(detailAdapter);


                                } else

                                {
                                    Log.d(TAG, " onResponse: " + response.errorBody());
                                }

                            }

                            @Override
                            public void onFailure(Call<PokemonDetail> call, Throwable t) {

                                Log.d(TAG, " onFailure: " + t.getMessage());
                                Toast.makeText(getApplicationContext(), "Please restart application", Toast.LENGTH_LONG);
                            }

                        }

        );


    }

    //request to return PokemonSpecies object

    public PokemonSpecies loadPokemonSpecies() {
        spinner.setVisibility(View.VISIBLE);

        PokeApi service = retrofit.create(PokeApi.class);
        Call<PokemonSpecies> call = service.getPokemonSpecies(PokemonAdapter.selectedPokemon);
        call.enqueue(new Callback<PokemonSpecies>() {
                         @Override
                         public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                             pSpecies = response.body();
                             p.setSpecies(pSpecies);

                             //return description text for a pokemon
                             String flavor = "";
                             for (int i = 0; i < pSpecies.getFlavourTextList().size(); i++) {
                                 if (i == 1) {
                                     flavor = pSpecies.getFlavourTextList().get(1).getFlavor_text();
                                     flavor.replaceAll("\\n", " ");

                                 }

                             }

                             int capture = pSpecies.getCapture_rate();
                             int baseHappiness = pSpecies.getBase_happiness();

                             //set description, capture_rate and base happiness in PokemonDetail
                             p.setDescription(flavor);
                             p.setCaptureRate(capture);
                             p.setBaseHappiness(baseHappiness);

                             //notify adapter that data has changed
                             detailAdapter.notifyDataSetChanged();

                         }

                         @Override
                         public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                             Log.e(TAG, " onFailure: " + t.getMessage());
                             Toast.makeText(getApplicationContext(), "Something seems to have gone wrong. Please wait and then try again!", Toast.LENGTH_SHORT);
                         }

                     }
        );
        return pSpecies;


    }


}









