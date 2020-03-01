package kve.ru.pizzarecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

  ImageView imageViewDetail;
  TextView textViewTitle;
  TextView textViewContent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    imageViewDetail = findViewById(R.id.imageViewDetail);
    textViewTitle = findViewById(R.id.textViewTitle);
    textViewContent = findViewById(R.id.textViewContent);

    Intent intent = getIntent();
    if (intent != null) {
      if (intent.hasExtra("imageResource")) {
        imageViewDetail.setImageResource(intent.getIntExtra("imageResource", 0));
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
