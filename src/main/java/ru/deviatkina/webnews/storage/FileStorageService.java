package ru.deviatkina.webnews.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.deviatkina.webnews.dao.ArticleDAO;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.modells.ArticleGroup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class FileStorageService  implements  StorageService {
    private final ArticleDAO articleDAO;

    public FileStorageService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Value("${upload.path}")
    private String path;

    public boolean store(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        if (fileName == null)
            return false;

        if (!FilenameUtils.getExtension(fileName).equals("zip"))
            return false;

        String header;
        String group;
        StringBuilder text;
        try (ZipInputStream stream = new ZipInputStream(file.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {

            ZipEntry zipEntry = stream.getNextEntry();

            if (zipEntry == null) {
                return false;
            }

            String textFileName = zipEntry.getName();
            if (!(textFileName).equals("article.txt")) {
                return false;
            }


            header = bufferedReader.readLine();
            if (header == null) {
                return false;
            }

            group = bufferedReader.readLine();
            if ((group == null) || !ArticleDAO.checkArticleGroupDAO(group)) {
                return false;
            }

            String textStr = bufferedReader.readLine();
            if (textStr == null) {
                return false;
            }
            text = new StringBuilder (textStr);
            while ((textStr = bufferedReader.readLine()) != null) {
                text.append(textStr);
            }

            stream.closeEntry();

            if ((stream.getNextEntry() != null)) {
                return false;
            }

            ArticleGroup articleGroup = null;

            for (ArticleGroup oneArticleGroup : ArticleGroup.values()) {
                if (oneArticleGroup.getArticleGroupInRus().equals(group)) {
                    articleGroup = oneArticleGroup;
                }
            }

            Article article = new Article();
            article.setArticleHeader(header);
            article.setArticleGroup(articleGroup);
            article.setArticleText(text.toString());
            articleDAO.save(article);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

/*

            if (file.isEmpty()) {
                return false;
            }
            try {
               fileName = file.getOriginalFilename();
                InputStream inputStream = file.getInputStream();

                Files.copy(inputStream, Paths.get(path + fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {

                //   var msg = String.format("Failed to store file %f", file.getName());

                //           throw new StorageException(msg, e);                                            обработка исключени!!!
                System.out.println("fail to read and save file");
            }

           */
        return true;
    }

}
