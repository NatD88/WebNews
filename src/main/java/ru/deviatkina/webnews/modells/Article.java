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

    static public ArticleGroup[] articleGroups = {ArticleGroup.ECONOMICS, ArticleGroup.POLITICS,
            ArticleGroup.TRAVELING, ArticleGroup.SPORTS};

    public Article() {
    }

    public Article(ArticleGroup articleGroup, String articleHeader, String articleText) {
       // this.articleId = ++ARTICLECOUNT;
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
        this.articleGroup = articleGroup;
    }

    public String getArticleHeader() {
        return articleHeader;
    }

    public void setArticleHeader(String articleHeader) {
        this.articleHeader = articleHeader;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }


    public static boolean checkArticleGroup (String group) {
        Set<String> setOfGroups = new HashSet<>(Arrays.asList(
                ArticleGroup.TRAVELING.getArticleGroupInRus(),
                ArticleGroup.ECONOMICS.getArticleGroupInRus(),
                ArticleGroup.POLITICS.getArticleGroupInRus(),
                ArticleGroup.SPORTS.getArticleGroupInRus()));
        return setOfGroups.contains(group);
    }
}
