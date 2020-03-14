package kve.ru.pizzarecipes.data;

public class IngredientGroup {
  private String title;
  private String content;

  public IngredientGroup(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }
}
