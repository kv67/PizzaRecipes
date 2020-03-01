package kve.ru.pizzarecipes;

public class PizzaRecipe {
  private int imageResource;
  private String header;
  private String shortInfo;
  private String content;

  public PizzaRecipe(int imageResource, String header, String shortInfo) {
    this.imageResource = imageResource;
    this.header = header;
    this.shortInfo = shortInfo;
  }

  public PizzaRecipe(int imageResource, String header, String shortInfo, String content) {
    this.imageResource = imageResource;
    this.header = header;
    this.shortInfo = shortInfo;
    this.content = content;
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

  public String getContent() {
    return content;
  }
}
