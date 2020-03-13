package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        String content =
            NetworkUtils.getCurrentRecipe(adapter.getRecipes().get(position).getHref());
        if (content != null) {
          adapter.getRecipes().get(position).setContent(content);
          Intent intent = new Intent(MainActivity.this, DetailActivity.class);
          intent.putExtra("imageSrc", adapter.getRecipes().get(position).getImageSrc());
          intent.putExtra("header", adapter.getRecipes().get(position).getHeader());
          intent.putExtra("content", adapter.getRecipes().get(position).getContent());
          startActivity(intent);
        }
      }
    });

    NetworkUtils.getRecipes();
  }

  private void loadRecipes(List<PizzaRecipe> recipes) {
    if (!loading) {
      setLoading(true);
      adapter.addRecipes(recipes);
      if (!recyclerViewMain.isComputingLayout()) {
        adapter.notifyDataSetChanged();
      }
      setLoading(false);
    }
  }

}
