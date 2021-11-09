package ru.deviatkina.webnews.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.deviatkina.webnews.dao.ArticleDAO;
import ru.deviatkina.webnews.modells.Article;

import javax.validation.Valid;


@Controller
@RequestMapping("/articles")
public class NewsController {

    private final ArticleDAO articleDAO;

    public NewsController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("article") Article article, Model model) {

        model.addAttribute("articles", articleDAO.index(article.getArticleGroup()));
        return "articles/index";
    }


    @GetMapping("/new")
    public String showNewArticlePage( @ModelAttribute("article") Article article, Model model) {
        model.addAttribute("article", new Article());
        return "articles/new";
    }

    @PostMapping()
    public String createNewArticle(@ModelAttribute("article") @Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "articles/new";
        else {
            articleDAO.save(article);
            return "redirect:/articles";
        }
    }

}