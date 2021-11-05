package ru.deviatkina.webnews.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.deviatkina.webnews.dao.ArticleDAO;
import ru.deviatkina.webnews.modells.Article;

@Controller
@RequestMapping("/articles")
public class NewsController {

    private final ArticleDAO articleDAO;

    public NewsController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
    /*
    @GetMapping("/news")
    public String showingAllNews(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "news";
    }
*/
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("articles", articleDAO.index());
        return "articles/index";
    }

    @GetMapping("/{id}")
    public String showOneArticle(@PathVariable ("id") int id, Model model) {
        model.addAttribute("article", articleDAO.show(id));
        return "articles/show";
    }

    //<a th:href="@{}"> Вернуться к списку статей </a>
    //<a th:href="@{articles/new}"> Добавить еще одну статью</a>

    @GetMapping("/new")
    public String showNewArticlePage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("article", new Article());
        return "articles/new";
    }

    @PostMapping()
    public String createNewArticle(@ModelAttribute("article") Article article) {
        articleDAO.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(Model model, @PathVariable("id") int id) {
        model.addAttribute("article", articleDAO.show(id));
        return "articles/edit";

    }

    @PostMapping("/{id}")  // тут должен быть Patch!!!
    public String updateArticle(@ModelAttribute("article") Article article, @PathVariable("id") int id) {
        articleDAO.update(id, article);
        return "redirect:/articles";
    }

    @PostMapping("/{id") // @DeleteMapping!!!!!!!!!!!!!!!!   и добавить фигурную скобку в параметр!!!!
    public String deleteArticle(@PathVariable("id") int id) {
        articleDAO.delete(id);
        return "redirect:/articles";
    }

    /*
    @GetMapping("/news/add")
    public String addingNews(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "addArticle";
    }
*/

    /*
        <select th:field="*{articleGroup}">
        <option th:each="article : ${cities}"
                th:value="${city.code}"
                th:text="${city.city}">
            Address
        </option>
    </select>
     */

    /*
    <label> Укажите тему статьи
        <select name = "articleGroup">
            <option th:each="articleGroup : ${article.articleGroups}" th:text="${articleGroup.getArticleGroupInRus()}">
            </option>

        </select>
    </label>


     */

}