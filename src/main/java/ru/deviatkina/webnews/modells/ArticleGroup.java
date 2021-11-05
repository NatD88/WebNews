package ru.deviatkina.webnews.modells;

public enum ArticleGroup {
    POLITICS("Политика"),
    ECONOMICS("Экономика"),
    SPORTS("Спорт"),
    TRAVELING("Путешествия");

    private final String articleGroupInRus;

    ArticleGroup(String articleGroupInRus) {
        this.articleGroupInRus = articleGroupInRus;
    }

    public String getArticleGroupInRus() {
        return articleGroupInRus;
    }
}


