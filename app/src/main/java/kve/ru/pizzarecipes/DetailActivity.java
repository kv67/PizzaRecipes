package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kve.ru.pizzarecipes.adapters.IngredientsAdapter;
import kve.ru.pizzarecipes.utils.NetworkUtils;

public class DetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    ScrollView scrollViewDetail = findViewById(R.id.scrollViewDetail);
    RecyclerView recyclerViewIngredients = findViewById(R.id.recyclerViewIngredients);
    IngredientsAdapter adapter = new IngredientsAdapter();
    recyclerViewIngredients.setAdapter(adapter);
    recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewIngredients.setHasFixedSize(false);

    ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
    TextView textViewTitle = findViewById(R.id.textViewTitle);
    TextView textViewContent = findViewById(R.id.textViewContent);

    Intent intent = getIntent();
    if (intent != null) {
      if (intent.hasExtra("imageSrc")) {
        Picasso.get().load(intent.getStringExtra("imageSrc")).placeholder(R.drawable.pizza).into(imageViewDetail);
      }
      if (intent.hasExtra("header")) {
        setTitle(intent.getStringExtra("header"));
        textViewTitle.setText(intent.getStringExtra("header"));
      }
      if (intent.hasExtra("content")) {
        textViewContent.setText(intent.getStringExtra("content"));
      }
      if (intent.hasExtra("href")) {
        adapter.setIngredients(NetworkUtils.getIngredients(intent.getStringExtra("href")));
      }
    }

    scrollViewDetail.smoothScrollTo(0, 0);
  }
}
