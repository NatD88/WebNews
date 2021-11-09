package ru.deviatkina.webnews;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import ru.deviatkina.webnews.modells.ArticleGroup;
import ru.deviatkina.webnews.service.ArticleStorageService;
import ru.deviatkina.webnews.service.FileStorageService;


import java.io.FileInputStream;

import java.util.Arrays;

@SpringBootTest
public class FileStorageServiceTest {
    @MockBean
    private ArticleStorageService articleStorageService;

    @Test
    public void testStore()  {

        FileStorageService fileStorageService = new FileStorageService(
                articleStorageService);
        try  {
            assert (fileStorageService.store(new MockMultipartFile("article.zip", new FileInputStream("article.zip"))).equals("Статья успешно сохранена"));
            assert (fileStorageService.store(new MockMultipartFile("web-news.zip", new FileInputStream("web-news.zip"))).equals("В архиве должен быть единственный файл с нахванием \"article.txt\" "));
            assert (fileStorageService.store(new MockMultipartFile("dgfdfg.docx", new FileInputStream("dgfdfg.docx"))).equals("Загруженный файл не является архивом"));
            assert (fileStorageService.store(new MockMultipartFile("ert.zip", new FileInputStream("ert.zip"))).equals("Файл \"article.txt\" содержит только две строку. Этого не достаточно для сохранения статьи. \n" +
                    "Формат файла: первая строка - заголовок, вторая строка - тема статьи, с третьей строки и далее - текст статьи"));
            assert (fileStorageService.store(new MockMultipartFile("feji.zip", new FileInputStream("feji.zip"))).equals("Некорректно указан формат темы статьи. \n" +
                    "Допустимые значения: " + Arrays.toString(ArticleGroup.values())));
            assert (fileStorageService.store(new MockMultipartFile("юра, марина.zip", new FileInputStream("юра, марина.zip"))).equals("Название zip-архива должно состоять из латинских букв, цифр, знака \"минус\"  и точки. \n " +
                    "Примеры названий: df.zip, sd-fsd.zip, vvv.zip, 12312.zip \n" +
                    "В названии не должно быть русских букв."));
            assert (fileStorageService.store(new MockMultipartFile("srgsrg.zip", new FileInputStream("srgsrg.zip"))).equals("Ошибка распаковки архива. Файл внутри архива не должен содержать русских символов. Файл должен называться \"article.txt\""));
        } catch (Exception e) {

        }
    }

    @Test
    public void regexForFileNameTest () {

        assert(FileStorageService.regexForFileName("sfgrgrdsg"));
        assert(!FileStorageService.regexForFileName("sпнгп"));
        assert(!FileStorageService.regexForFileName("шjijijiji"));
        assert(FileStorageService.regexForFileName("gjy-dyjj.com"));
    }


}
