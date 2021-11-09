package ru.deviatkina.webnews.modells;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

public class Article {
    private static int ARTICLECOUNT;
    private final int articleId = ++ARTICLECOUNT;
    private ArticleGroup articleGroup;
    @Size(min = 1, max = 300, message = "Заголовок статьи должен содержать от 1 до 300 символов")
    private String articleHeader;
    @NotEmpty(message = "Текст статьи не должен быть пустым")
    private String articleText;

    static Set<String> setOfGroups = new HashSet<>(Arrays.asList(
            ArticleGroup.TRAVELING.getArticleGroupInRus(),
            ArticleGroup.ECONOMICS.getArticleGroupInRus(),
            ArticleGroup.POLITICS.getArticleGroupInRus(),
            ArticleGroup.SPORTS.getArticleGroupInRus()));

    static public ArticleGroup[] articleGroups = {ArticleGroup.ECONOMICS, ArticleGroup.POLITICS,
            ArticleGroup.TRAVELING, ArticleGroup.SPORTS};

    public Article() {
    }

    public Article(ArticleGroup articleGroup, String articleHeader, String articleText) {

        if (articleGroup == null) {
            throw new RuntimeException("articleGroup is null! can't create article!");
        }

        if (articleHeader == null) {
            throw new RuntimeException("articleHeader is null! can't create article!");
        }

        if (articleText == null) {
            throw new RuntimeException("articleText is null! can't create article!");
        }

        this.articleGroup = articleGroup;
        this.articleHeader = articleHeader;
        this.articleText = articleText;
    }

    public int getArticleId() {
        return articleId;
    }

    public ArticleGroup getArticleGroup() {
        return articleGroup;
    }

    public void setArticleGroup(ArticleGroup articleGroup) {
        if (articleGroup == null) {
            throw new RuntimeException("articleGroup is null! can't create article!");
        } else {
            this.articleGroup = articleGroup;
        }
    }

    public String getArticleHeader() {
        return articleHeader;
    }

    public void setArticleHeader(String articleHeader) {
        if (articleHeader == null) {
            throw new RuntimeException("articleHeader is null! can't create article!");
        } else {
            this.articleHeader = articleHeader;
        }
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        if (articleText == null) {
            throw new RuntimeException("articleText is null! can't create article!");
        } else {
            this.articleText = articleText;
        }
    }


    public static boolean checkArticleGroup(String group) {
        return setOfGroups.contains(group);
    }

    public static ArticleGroup transformStringGroupToEnumArticleGroup(String StrGroup) {

        if (StrGroup == null) {
            throw new RuntimeException("StrGroup in null!");
        }

        for (ArticleGroup oneArticleGroup : ArticleGroup.values()) {
            if (oneArticleGroup.getArticleGroupInRus().equals(StrGroup)) {
                return oneArticleGroup;
            }
        }
        throw new RuntimeException("StrGroup doesn't match any ArticleGroup");
    }
}
