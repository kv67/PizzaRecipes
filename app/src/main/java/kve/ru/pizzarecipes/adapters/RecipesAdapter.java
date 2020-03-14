package kve.ru.pizzarecipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kve.ru.pizzarecipes.R;
import kve.ru.pizzarecipes.data.PizzaRecipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

  private static List<PizzaRecipe> recipes;
  private OnRecipeClickListener onRecipeClickListener;
  private OnReachEndListener onReachEndListener;

  public RecipesAdapter() {
    if (recipes == null) {
      recipes = new ArrayList<>();
    }
  }

  public static List<PizzaRecipe> getRecipes() {
    return recipes;
  }

  public void addRecipes(List<PizzaRecipe> recipes) {
    if (recipes != null && !recipes.isEmpty()) {
      this.recipes.addAll(recipes);
    }
  }

  public interface OnRecipeClickListener {
    void onRecipeClick(int position);
  }

  public interface OnReachEndListener {
    void onReachEnd();
  }

  public void setOnRecipeClickListener(OnRecipeClickListener onRecipeClickListener) {
    this.onRecipeClickListener = onRecipeClickListener;
  }

  public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
    this.onReachEndListener = onReachEndListener;
  }

  @NonNull
  @Override
  public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent,
        false);
    return new RecipesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
    if (recipes.size() >= 25 && position == recipes.size() - 15 && onReachEndListener != null) {
      onReachEndListener.onReachEnd();
    }

    Picasso.get().load(recipes.get(position).getImageSrc()).placeholder(R.drawable.pizza).into(holder.imageViewPizza);
    holder.textViewHeader.setText(recipes.get(position).getHeader());
    holder.textViewContent.setText(recipes.get(position).getShortInfo());
  }

  @Override
  public int getItemCount() {
    return recipes.size();
  }

  class RecipesViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageViewPizza;
    private TextView textViewHeader;
    private TextView textViewContent;

    public RecipesViewHolder(@NonNull View itemView) {
      super(itemView);
      imageViewPizza = itemView.findViewById(R.id.imageViewDetail);
      textViewHeader = itemView.findViewById(R.id.textViewHeader);
      textViewContent = itemView.findViewById(R.id.textViewContent);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (onRecipeClickListener != null) {
            onRecipeClickListener.onRecipeClick(getAdapterPosition());
          }
        }
      });
    }
  }
}
