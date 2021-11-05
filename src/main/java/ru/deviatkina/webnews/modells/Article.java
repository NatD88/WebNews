package ru.deviatkina.webnews.modells;


public class Article {
    private int articleId;
    private ArticleGroup articleGroup;
    private String articleHeader;
    private String articleText;

    public ArticleGroup[] articleGroups = {ArticleGroup.ECONOMICS, ArticleGroup.POLITICS,
            ArticleGroup.TRAVELING, ArticleGroup.SPORTS};

    public Article() {
    }

    public Article(int articleId, ArticleGroup articleGroup, String articleHeader, String articleText) {
        this.articleId = articleId;
        this.articleGroup = articleGroup;
        this.articleHeader = articleHeader;
        this.articleText = articleText;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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


}
