package ru.deviatkina.webnews.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.modells.ArticleGroup;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDAO {
    private static int ARTICLECOUNT;
    private List<Article> articles;
    {
        articles  = new ArrayList<>();
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ,"New world", "Once upon a time..."));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ," In New  little world", "Once upon a time ... Once upon a time ... "));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.ECONOMICS ,"Money", "Once upon a time... money money"));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.SPORTS ,"Football", "Once upon a time... football football"));
        articles.add(new Article(++ARTICLECOUNT, ArticleGroup.TRAVELING ,"Spain", "Once upon a time... Spain Madrid"));
    }

/*
   public List<Article> articlesInit(){
       articles  = new ArrayList<>();
       articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ,"New world", "Once upon a time..."));
       articles.add(new Article(++ARTICLECOUNT, ArticleGroup.POLITICS ," In New  little world", "Once upon a time ... Once upon a time ... "));
       articles.add(new Article(++ARTICLECOUNT, ArticleGroup.ECONOMICS ,"Money", "Once upon a time... money money"));
       articles.add(new Article(++ARTICLECOUNT, ArticleGroup.SPORTS ,"Football", "Once upon a time... football football"));
       articles.add(new Article(++ARTICLECOUNT, ArticleGroup.TRAVELING ,"Spain", "Once upon a time... Spain Madrid"));
       return articles;
   }
*/
   public List<Article> index(ArticleGroup selectedArticleGroup) {

       if (selectedArticleGroup == null) {
           return articles;
       } else {
           List<Article> selectedArticles = new ArrayList<>();
           for (Article article : articles) {
               if (article.getArticleGroup().getArticleGroupInRus().equals(selectedArticleGroup.getArticleGroupInRus())) {
                   selectedArticles.add(article);
               }
           }
           return selectedArticles;
       }
   }



   public Article show(int articleID) {
       return articles.stream()
               .filter(article -> article.getArticleId() ==articleID)
               .findAny()
               .orElse(null);
   }

   public void save(Article article) {
       article.setArticleId(++ARTICLECOUNT);
      //    article.setArticleGroup(ArticleGroup.POLITICS); // это неправильно!!!!! нужно добавить на форму ввода select
       articles.add(article);
   }

   public void update(int articleId, Article updatedArticle) {
       Article articleToUpdate = show(articleId);
       articleToUpdate.setArticleGroup(updatedArticle.getArticleGroup());
     //  articleToUpdate.setArticleGroup(ArticleGroup.SPORTS);  // это неправильно!!!!! нужно добавить на форму ввода select
       articleToUpdate.setArticleHeader(updatedArticle.getArticleHeader());
       articleToUpdate.setArticleText(updatedArticle.getArticleText());
   }

   public void delete(int articleID) {
       articles.removeIf(article -> article.getArticleId() == articleID);
   }

}
