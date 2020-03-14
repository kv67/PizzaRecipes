package kve.ru.pizzarecipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kve.ru.pizzarecipes.R;
import kve.ru.pizzarecipes.data.IngredientGroup;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

  private List<IngredientGroup> ingredients = new ArrayList<>();

  @NonNull
  @Override
  public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients, parent,
        false);
    return new IngredientsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
    holder.textViewTitle.setText(ingredients.get(position).getTitle());
    holder.textViewContent.setText(ingredients.get(position).getContent());
  }

  @Override
  public int getItemCount() {
    return ingredients.size();
  }

  class IngredientsViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewTitle;
    private TextView textViewContent;

    public IngredientsViewHolder(@NonNull View itemView) {
      super(itemView);
      textViewTitle = itemView.findViewById(R.id.textViewTitle);
      textViewContent = itemView.findViewById(R.id.textViewContent);
    }
  }
}
