package ru.deviatkina.webnews.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.service.ArticleStorageService;
import javax.validation.Valid;


@Controller
@RequestMapping("/articles")
public class NewsController {

    private final ArticleStorageService articleStorageService;

    public NewsController(ArticleStorageService articleStorageService) {
        this.articleStorageService = articleStorageService;
    }

    @GetMapping()
    public String index(@ModelAttribute("article") Article article, Model model) {

        model.addAttribute("articles",
                articleStorageService.index(article.getArticleGroup()) );
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
            articleStorageService.save(article);
            return "redirect:/articles";
        }
    }

}