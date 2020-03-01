package kve.ru.pizzarecipes;

public class PizzaRecipe {
  private int imageResource;
  private String header;
  private String shortInfo;

  public PizzaRecipe(int imageResource, String header, String shortInfo) {
    this.imageResource = imageResource;
    this.header = header;
    this.shortInfo = shortInfo;
  }

  public int getImageResource() {
    return imageResource;
  }

  public String getHeader() {
    return header;
  }

  public String getShortInfo() {
    return shortInfo;
  }
}
