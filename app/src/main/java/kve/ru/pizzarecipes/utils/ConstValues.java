package kve.ru.pizzarecipes.utils;

public class ConstValues {

  private ConstValues() {
    throw new IllegalStateException("ConstValues class");
  }

  public static final String WEB_SITE = "https://www.bbcgoodfood.com";
  public static final String NEXT_PAGE = "/recipes/collection/pizza?page=%d";
  public static final String ARTICLE = "article[class*=node node-recipe node-teaser-item clearfix]";
  public static final String ITEM_TITLE = ".teaser-item__title";
  public static final String IMAGE_SRC = "img[src*=.jpg]";
  public static final String ITEM_CONTENT = "div.teaser-item__text-content";
  public static final String HTTPS = "https:";
  public static final String METHOD_ITEM = "li[class=method__item]";
}
