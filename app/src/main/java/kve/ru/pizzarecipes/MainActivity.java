package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kve.ru.pizzarecipes.adapters.RecipesAdapter;
import kve.ru.pizzarecipes.data.PizzaRecipe;
import kve.ru.pizzarecipes.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

  private static boolean loading = false;
  private RecyclerView recyclerViewMain;
  private RecipesAdapter adapter;

  private static void setLoading(boolean loading) {
    MainActivity.loading = loading;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    NetworkUtils.setOnFinishLoadingListener(new NetworkUtils.OnFinishLoadingListener() {
      @Override
      public void onFinishLoading(List<PizzaRecipe> recipes) {
        loadRecipes(recipes);
      }
    });
    recyclerViewMain = findViewById(R.id.recyclerViewMain);
    adapter = new RecipesAdapter();
    recyclerViewMain.setAdapter(adapter);
    recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
    adapter.setOnReachEndListener(new RecipesAdapter.OnReachEndListener() {
      @Override
      public void onReachEnd() {
        NetworkUtils.getRecipes();
      }
    });
    adapter.setOnRecipeClickListener(new RecipesAdapter.OnRecipeClickListener() {
      @Override
      public void onRecipeClick(int position) {
        String content = NetworkUtils.getCurrentRecipe(RecipesAdapter.getRecipes().get(position).getHref());
        if (content != null) {
          RecipesAdapter.getRecipes().get(position).setContent(content);
          Intent intent = new Intent(MainActivity.this, DetailActivity.class);
          intent.putExtra("imageSrc", RecipesAdapter.getRecipes().get(position).getImageSrc());
          intent.putExtra("header", RecipesAdapter.getRecipes().get(position).getHeader());
          intent.putExtra("content", RecipesAdapter.getRecipes().get(position).getContent());
          intent.putExtra("href", RecipesAdapter.getRecipes().get(position).getHref());
          startActivity(intent);
        }
      }
    });

    NetworkUtils.getRecipes();
  }

  private void loadRecipes(List<PizzaRecipe> recipes) {
    if (!loading) {
      setLoading(true);
      RecipesAdapter.addRecipes(recipes);
      if (!recyclerViewMain.isComputingLayout()) {
        adapter.notifyDataSetChanged();
      }
      setLoading(false);
    }
  }

}
