package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kve.ru.pizzarecipes.adapters.IngredientsAdapter;

public class DetailActivity extends AppCompatActivity {

  private RecyclerView recyclerViewIngredients;
  private IngredientsAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    recyclerViewIngredients = findViewById(R.id.recyclerViewIngredients);
    adapter = new IngredientsAdapter();
    recyclerViewIngredients.setAdapter(adapter);
    recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(this));

    ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
    TextView textViewTitle = findViewById(R.id.textViewTitle);
    TextView textViewContent = findViewById(R.id.textViewContent);

    Intent intent = getIntent();
    if (intent != null) {
      if (intent.hasExtra("imageSrc")) {
        Picasso.get().load(intent.getStringExtra("imageSrc")).placeholder(R.drawable.pizza).into(imageViewDetail);
      }
      if (intent.hasExtra("header")) {
        textViewTitle.setText(intent.getStringExtra("header"));
      }
      if (intent.hasExtra("content")) {
        textViewContent.setText(intent.getStringExtra("content"));
      }
    }

  }
}
