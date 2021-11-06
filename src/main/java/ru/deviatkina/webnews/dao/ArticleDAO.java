package ru.deviatkina.webnews.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.modells.ArticleGroup;

import java.util.*;

@Component
public class ArticleDAO {
    private static int ARTICLECOUNT;

     public static boolean checkArticleGroupDAO (String group) {
         return Article.checkArticleGroup(group);
    }

    private LinkedList<Article> articles;
    {

        articles = new LinkedList<>();
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ,"New world", "Once upon a time..."));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ," In New  little world", "Once upon a time ... Once upon a time ... "));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.ECONOMICS ,"Money", "Once upon a time... money money"));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.SPORTS ,"Football", "Once upon a time... football football"));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.TRAVELING ,"Spain", "Once upon a time... Spain Madrid"));
    }

   public List<Article> index(ArticleGroup selectedArticleGroup) {

       if (selectedArticleGroup == null) {
           return articles;
       } else {
           List<Article> selectedArticles = new LinkedList<>();
           for (Article article : articles) {
               if (article.getArticleGroup().getArticleGroupInRus().equals(selectedArticleGroup.getArticleGroupInRus())) {
                   selectedArticles.add(article);
               }
           }
           return selectedArticles;
       }
   }

   public Article findArticleById(int articleID) {
       return articles.stream()
               .filter(article -> article.getArticleId() ==articleID)
               .findAny()
               .orElse(null);
   }

    public void save(Article article) {
        article.setArticleId(++ARTICLECOUNT);
        articles.addFirst(article);
    }

    public void update(int articleId, Article updatedArticle) {
        Article articleToUpdate = findArticleById(articleId);
        articleToUpdate.setArticleGroup(updatedArticle.getArticleGroup());
        articleToUpdate.setArticleHeader(updatedArticle.getArticleHeader());
        articleToUpdate.setArticleText(updatedArticle.getArticleText());
    }

   public void delete(int articleID) {
       articles.removeIf(article -> article.getArticleId() == articleID);
   }

}
