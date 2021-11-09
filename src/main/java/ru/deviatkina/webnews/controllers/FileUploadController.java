package ru.deviatkina.webnews.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.service.FileStorageService;
import ru.deviatkina.webnews.service.StorageService;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/doUpload")
    public String upload(@ModelAttribute("file")
                         @RequestParam MultipartFile file, Model model) {

       String storeMessage = storageService.store(file);
       model.addAttribute("message", storeMessage);
        if (storeMessage.equals(FileStorageService.SUCCESS_STORE)) {
            return "redirect:/articles";
        } else {
            return "articles/fail_to_store";
        }
    }
}
