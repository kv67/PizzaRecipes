package kve.ru.pizzarecipes.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kve.ru.pizzarecipes.data.IngredientGroup;
import kve.ru.pizzarecipes.data.PizzaRecipe;

public class NetworkUtils {

  private static final String TAG = "NetworkUtils";
  private static int nextPage = 0;
  private static boolean isLoading = false;
  private static OnFinishLoadingListener onFinishLoadingListener;

  public interface OnFinishLoadingListener {
    void onFinishLoading(List<PizzaRecipe> recipes);
  }

  public static void setOnFinishLoadingListener(OnFinishLoadingListener onFinishLoadingListener) {
    NetworkUtils.onFinishLoadingListener = onFinishLoadingListener;
  }

  public static List<IngredientGroup> getIngredients(String href) {
    List<IngredientGroup> result = new ArrayList<>();
    try {
      result = new LoadIngredientsTask().execute(href).get();
    } catch (ExecutionException e) {
      Log.e(TAG, e.getLocalizedMessage());
    } catch (InterruptedException e) {
      Log.e(TAG, e.getLocalizedMessage());
      Thread.currentThread().interrupt();
    }
    return result;
  }

  public static String getCurrentRecipe(String href) {
    try {
      return new LoadRecipeTask().execute(href).get();
    } catch (ExecutionException e) {
      Log.e(TAG, e.getLocalizedMessage());
    } catch (InterruptedException e) {
      Log.e(TAG, e.getLocalizedMessage());
      Thread.currentThread().interrupt();
    }
    return null;
  }

  public static void getRecipes() {
    if (!isLoading) {
      List<PizzaRecipe> recipes = null;
      try {
        recipes = new LoadingTask().execute().get();
        if (onFinishLoadingListener != null) {
          onFinishLoadingListener.onFinishLoading(recipes);
        }
      } catch (ExecutionException e) {
        Log.e(TAG, e.getLocalizedMessage());
      } catch (InterruptedException e) {
        Log.e(TAG, e.getLocalizedMessage());
        Thread.currentThread().interrupt();
      }
    }
  }

  public static class LoadIngredientsTask extends AsyncTask<String, Void, List<IngredientGroup>> {

    @Override
    protected List<IngredientGroup> doInBackground(String... href) {
      List<IngredientGroup> ingredients = new ArrayList<>();
      String ref = href[0];
      Document doc;
      try {
        doc = Jsoup.connect(ConstValues.WEB_SITE + ref).get();
        Elements groupTitles =
            doc.select(ConstValues.INGREDIENTS_CONTENT).select(ConstValues.INGREDIENTS_GROUP_TITLE);
        Elements groupContents =
            doc.select(ConstValues.INGREDIENTS_CONTENT).select(ConstValues.INGREDIENTS_LIST_GROUP);
        for (int i = 0; i < groupContents.size(); i++) {
          String title = "Ingredients:";
          Elements groupItems = groupContents.get(i).select(ConstValues.INGREDIENTS_ITEMS);
          if (groupItems == null || groupItems.isEmpty()) {
            continue;
          }
          if (i > 0 && groupTitles.size() > (i - 1)) {
            title = groupTitles.get(i - 1).text();
          }
          StringBuilder bld = new StringBuilder();
          for (Element element : groupContents.get(i).select(ConstValues.INGREDIENTS_ITEMS)) {
            bld.append("   " + element.attr("content") + "\n");
          }
          ingredients.add(new IngredientGroup(title, bld.toString()));
        }

      } catch (IOException e) {
        Log.e(TAG, "LoadIngredientsTask. Error during parsing html document: " + e.getMessage());
      }

      return ingredients;
    }
  }

  public static class LoadRecipeTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... href) {
      String ref = href[0];
      Document pizzaDoc;
      try {
        pizzaDoc = Jsoup.connect(ConstValues.WEB_SITE + ref).get();
        Elements elements = pizzaDoc.select(ConstValues.METHOD_ITEM);
        StringBuilder bld = new StringBuilder();
        int i = 0;
        for (Element element : elements) {
          bld.append(++i + ".  " + element.text() + "\n\n");
        }
        return bld.toString();
      } catch (IOException e) {
        Log.e(TAG, "LoadRecipeTask. Error during parsing html document: " + e.getMessage());
      }
      return null;
    }
  }

  public static class LoadingTask extends AsyncTask<Void, Void, List<PizzaRecipe>> {

    private static void setNextPage(int nextPage) {
      NetworkUtils.nextPage = nextPage;
    }

    private static void setIsLoading(boolean isLoading) {
      NetworkUtils.isLoading = isLoading;
    }

    @Override
    protected List<PizzaRecipe> doInBackground(Void... voids) {
      List<PizzaRecipe> recipes = new ArrayList<>();
      setIsLoading(true);
      try {
        Document pizzaDoc;
        pizzaDoc = Jsoup.connect(ConstValues.WEB_SITE + String.format(ConstValues.NEXT_PAGE,
            nextPage)).get();
        Elements elements = pizzaDoc.select(ConstValues.ARTICLE);
        if (elements != null && !elements.isEmpty()) {
          String recipeTitle = "";
          String recipeHref = "";
          String recipeImage = "";
          String recipeInfo = "";

          for (Element element : elements) {
            Element title = element.select(ConstValues.ITEM_TITLE).first();
            if (title != null) {
              recipeTitle = title.text();
              recipeHref = title.select("a").attr("href");
            }
            Element img = element.select(ConstValues.IMAGE_SRC).first();
            if (img != null) {
              recipeImage = img.attr("src");
              recipeImage = ConstValues.HTTPS + recipeImage.substring(0, recipeImage.indexOf('?'));
            }
            Element info = element.select(ConstValues.ITEM_CONTENT).first();
            if (info != null) {
              recipeInfo = info.text();
            }
            if (!recipeTitle.isEmpty() && !recipeHref.isEmpty()) {
              recipes.add(new PizzaRecipe(recipeTitle, recipeInfo, recipeImage, recipeHref));
            }
          }

          setNextPage(nextPage + 1);
        }

      } catch (IOException e) {
        Log.e(TAG, "LoadingTask. Error during parsing html document: " + e.getMessage());
      }
      setIsLoading(false);
      return recipes;
    }
  }
}
