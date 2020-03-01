package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  List<PizzaRecipe> recipes = new ArrayList<>();
  RecyclerView recyclerViewMain;
  RecipesAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recipes.add(new PizzaRecipe(R.drawable.marghuerita_next_level,
        "Next level Margherita pizza",
        "Forget takeaways – you can’t beat a homemade Margherita pizza topped with fresh tomato" + " sauce and melted cheese. Here's how to master this everyday classic.....", ConstValues.MARGHUERITA_NEXT_LEVEL));
    recipes.add(new PizzaRecipe(R.drawable.margherita_in_four_steps,
        "Pizza Margherita in 4 easy steps",
        "Even a novice cook can master the art of pizza with our simple step-by-step guide. " + "Bellissimo", ConstValues.MARGHERITA_IN_FOUR_STEPS));
    recipes.add(new PizzaRecipe(R.drawable.superhealthy_pizza,
        "Superhealthy pizza",
        "The quantities for this are generous, so if you have any leftovers, pop a wedge of " +
            "cold pizza into your lunchbox the next day", ConstValues.SUPERHEALTHY_PIZZA));

    recyclerViewMain = findViewById(R.id.recyclerViewMain);
    recyclerViewMain.setHasFixedSize(true);
    adapter = new RecipesAdapter(recipes);
    recyclerViewMain.setAdapter(adapter);
    recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
    adapter.setOnRecipeClickListener(new RecipesAdapter.OnRecipeClickListener() {
      @Override
      public void onRecipeClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("imageResource", recipes.get(position).getImageResource());
        intent.putExtra("header", recipes.get(position).getHeader());
        intent.putExtra("content", recipes.get(position).getContent());
        startActivity(intent);
      }
    });

  }
}
