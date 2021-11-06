package ru.deviatkina.webnews.controllers;


import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.deviatkina.webnews.dao.ArticleDAO;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.storage.StorageException;
import ru.deviatkina.webnews.storage.StorageFileNotFoundException;
import ru.deviatkina.webnews.storage.StorageService;

@Controller
@RequestMapping("/files")
public class FileUploadController {
   // private final ArticleDAO articleDAO;

 //   public FileUploadController(ArticleDAO articleDAO) {
 //       this.articleDAO = articleDAO;
  //  }


    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    public String index(
         //   @ModelAttribute("article") Article article, Model model
    ) {

      //  model.addAttribute("articles", articleDAO.index(article.getArticleGroup()));
        return "files/index";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public String upload(@RequestParam MultipartFile file) {

        boolean successfulStore = storageService.store(file);
        if (successfulStore) {
            return "redirect:/articles";
        } else {
            return "files/failure";
        }
    }
/*
    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {

        return "files/failure";
    }
    */

}
