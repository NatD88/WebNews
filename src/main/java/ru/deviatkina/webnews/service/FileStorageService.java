package ru.deviatkina.webnews.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.deviatkina.webnews.modells.ArticleGroup;
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;

import java.util.zip.ZipInputStream;

@Component
public class FileStorageService {

    public static final String TEXT_FILE_NAME = "article.txt";
    public static final String FILE_EXTENTION = "zip";
    public static final String INCORRECT_ZIP_FILE = "Название zip-архива должно состоять из латинских букв, цифр, знака \"минус\"  и точки. \n " +
            "Примеры названий: df.zip, sd-fsd.zip, vvv.zip, 12312.zip \n" +
            "В названии не должно быть русских букв.";
    public static final String SUCCESS_STORE = "Статья успешно сохранена";
    public static final String NO_FILE = "Не выбран файл для загрузки";
    public static final String FILE_NOT_ZIP = "Загруженный файл не является архивом";
    public static final String ZIP_FILE_EMPTY = "Файл с архивом пуст";
    public static final String INCORRECT_FILE_NAME = "В архиве должен быть единственный файл с нахванием \"article.txt\" ";
    public static final String TEXT_FILE_EMPTY = "Файл \"article.txt\" пуст";
    public static final String TEXT_FILE_ONLY_ONE_LINE = "Файл \"article.txt\" содержит только одну строку. Этого не достаточно для сохранения статьи.\n" +
            "Формат файла: первая строка - заголовок, вторая строка - тема статьи, с третьей строки и далее - текст статьи";
    public static final String INCORRECT_GROUP = "Некорректно указан формат темы статьи. \n" +
            "Допустимые значения: ";
    public static final String TEXT_FILE_ONLY_TWO_LINES = "Файл \"article.txt\" содержит только две строку. Этого не достаточно для сохранения статьи. \n" +
            "Формат файла: первая строка - заголовок, вторая строка - тема статьи, с третьей строки и далее - текст статьи";
    public static final String ZIP_FILE_CONTAINS_MORE_FILES = "Файл с архивом не должен содержать никаких других файлов, кроме \"article.txt\" ";
    public static final String EXCEPTION_STORE_MESSAGE = "Ошибка чтения из файла, статья не сохранена";
    public static final String EXEPTION_ZIP_MESSAGE = "Ошибка распаковки архива. Файл внутри архива не должен содержать русских символов. Файл должен называться \"article.txt\"";


    private final ArticleStorageService articleStorageService;

    @Autowired
    public FileStorageService(ArticleStorageService articleStorageService) {

          this.articleStorageService = articleStorageService;
    }

    public String store(MultipartFile file)  {

        String fileName = file.getOriginalFilename();
        if ((fileName == null) || (fileName.equals(""))) {
            return NO_FILE;
        }

        if (!FilenameUtils.getExtension(fileName).equals(FILE_EXTENTION)) {
            return FILE_NOT_ZIP;
        }

        if (!regexForFileName(fileName)) {
            return INCORRECT_ZIP_FILE;
        }

        String header;
        String group;
        StringBuilder text;
        try (ZipInputStream stream = new ZipInputStream(file.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {

            // catches errors, when file name inside zip contains russian letters
            try {
                ZipEntry zipEntry = stream.getNextEntry();

                if (zipEntry == null) {
                    return ZIP_FILE_EMPTY;
                }

                String textFileName = zipEntry.getName();
                if (!(textFileName).equals(TEXT_FILE_NAME)) {
                    return INCORRECT_FILE_NAME;
                }

                header = bufferedReader.readLine();
                if (header == null) {
                    return TEXT_FILE_EMPTY;
                }

                group = bufferedReader.readLine();
                if (group == null) {
                    return TEXT_FILE_ONLY_ONE_LINE;
                }

                if (!ArticleStorageService.checkArticleGroupDAO(group)) {
                    return INCORRECT_GROUP + Arrays.toString(ArticleGroup.values());

                }

                String textStr = bufferedReader.readLine();
                if (textStr == null) {
                    return TEXT_FILE_ONLY_TWO_LINES;
                }
                text = new StringBuilder(textStr);
                while ((textStr = bufferedReader.readLine()) != null) {
                    text.append(textStr);
                }

                stream.closeEntry();

                if ((stream.getNextEntry() != null)) {
                    return ZIP_FILE_CONTAINS_MORE_FILES;
                }

                articleStorageService.create(header, group, text.toString());

            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
                return EXEPTION_ZIP_MESSAGE;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return EXCEPTION_STORE_MESSAGE;
        }

        return SUCCESS_STORE;
    }

    static public boolean regexForFileName(String name) {

        return name.matches("[a-zA-Z1-9.-]+");
    }

}
