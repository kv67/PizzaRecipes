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
  public static final String INGREDIENTS_CONTENT = "div[class=ingredients-list]";
  public static final String INGREDIENTS_LIST_GROUP = "ul[class=ingredients-list__group]";
  public static final String INGREDIENTS_GROUP_TITLE = "h3[class=ingredients-list__group-title]";
  public static final String INGREDIENTS_ITEMS = "li[class=ingredients-list__item]";
}
