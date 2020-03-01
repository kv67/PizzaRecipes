package kve.ru.pizzarecipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

  private List<PizzaRecipe> recipes;
  private OnRecipeClickListener onRecipeClickListener;

  public RecipesAdapter(List<PizzaRecipe> recipes) {
    this.recipes = recipes;
  }

  public interface OnRecipeClickListener {
    void onRecipeClick(int position);
  }

  public void setOnRecipeClickListener(OnRecipeClickListener onRecipeClickListener) {
    this.onRecipeClickListener = onRecipeClickListener;
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
    holder.imageViewPizza.setImageResource(recipes.get(position).getImageResource());
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
