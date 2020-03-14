package kve.ru.pizzarecipes.data;

public class PizzaRecipe {
  private int imageResource;
  private String header;
  private String shortInfo;
  private String content;
  private String imageSrc;
  private String href;

  public PizzaRecipe(String header, String shortInfo, String imageSrc, String href) {
    this.header = header;
    this.shortInfo = shortInfo;
    this.imageSrc = imageSrc;
    this.href = href;
  }

  public PizzaRecipe(int imageResource, String header, String shortInfo, String content) {
    this.imageResource = imageResource;
    this.header = header;
    this.shortInfo = shortInfo;
    this.content = content;
  }

  public void setContent(String content) {
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

  public String getImageSrc() {
    return imageSrc;
  }

  public String getHref() {
    return href;
  }
}
