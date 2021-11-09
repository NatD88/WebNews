package ru.deviatkina.webnews.service;

import org.springframework.stereotype.Component;
import ru.deviatkina.webnews.modells.Article;
import ru.deviatkina.webnews.modells.ArticleGroup;

import java.util.ArrayDeque;
import java.util.Deque;

@Component
public class ArticleStorageService {

    public static boolean checkArticleGroupDAO (String group) {
        return Article.checkArticleGroup(group);
    }


    private Deque<Article> articles;
    //  private Map <Integer, Article> searchingMap;

    {
        articles = new ArrayDeque<>();
        //   searchingMap = new HashMap<>();
        articles.addFirst(new Article(
                ArticleGroup.POLITICS ,"Жириновский назвал все революции отвратительными",
                "Лидер ЛДПР Владимир Жириновский раскритиковал действия большевиков во время Октябрьской революции 1917 года, назвал все революции отвратительными," +
                        "а также затронул тему вакцинации. Кроме того, лидер партии отметил, что до событий 1917 года Россия была в числе мировых лидеров в экономическом плане," +
                        " а в наши дни только стремится к этому.\n" +
                        "Жириновский затронул и проблему вакцинации и выразил сожаление, что еще не 100% студентов Института мировых цивилизаций (учрежден лидером ЛДПР) сделали прививки от коронавируса.\n" +
                        "\"Царь запретил бы КПРФ, \"Справедливую Россию\" и \"Новых людей\" в связи с их позицией по вакцинации\", - добавил политик."));
        //     searchingMap.put(ARTICLECOUNT, articles.getFirst());
        articles.addFirst(new Article(
                ArticleGroup.POLITICS ," Парламенты РФ и Белоруссии разработают \"дорожную карту\" интеграции",
                "Председатель Госудумы Володин рассказал о дальнейшей работе над \"дорожной картой\" интеграции России и Белоруссии" +
                        "Парламентам РФ и Белоруссии предстоит выработать \"дорожную карту\" участия депутатов в реализации интеграционных процессов, сообщил председатель Госдумы Вячеслав Володин.\n" +
                        "В четверг в режиме видеоконференции прошло заседание Высшего Госсовета Союзного государства России и Белоруссии. По его итогам был подписан ряд документов." +
                        "По его словам, на ближайшем заседании Парламентского собрания предстоит обсудить совместные шаги по законодательному обеспечению решений президентов двух стран, а также выработать \"дорожную карту\" участия депутатов в реализации интеграционных процессов.\n" +
                        "Ранее радио Sputnik сообщило, что президенты РФ и Белоруссии Владимир Путин и Александр Лукашенко подписали Декрет Союзного государства."));
        //     searchingMap.put(ARTICLECOUNT, articles.getFirst());
        articles.addFirst(new Article(
                ArticleGroup.ECONOMICS ,"Эксперт оценил защищенность российских банков",
                "Мошенникам все равно, в какой именно форме люди хранят свои сбережения, поскольку в основном им удается убедить жертву добровольно отдать свои средства, " +
                        "а защищенность банковских систем и приложений банков от взлома хакеров далеко не идеальна, " +
                        "заявил РИА Новости директор по методологии и стандартизации Positive Technologies Дмитрий Кузнецов." +
                        "Он добавил, что в этом случае все зависит от жертвы, нет принципиальной разницы между защищенностью различных форм хранения сбережений. " +
                        "Помимо воздействия на жертву, преступники используют различные методы манипуляций с банками.\n" +
                        "Банковские продукты, допускающие дистанционный доступ к денежным средствам, такие как депозит или карточный счет," +
                        " потенциально уязвимы для \"хакерских\" методов получения доступа, при этом преступники могут атаковать как сам банк, " +
                        "так и домашний компьютер или смартфон клиента, указал Кузнецов.\n" +
                        "Более \"традиционные\" методы хранения сбережений - срочные вклады, банковские ячейки, металлические счета" +
                        " - потенциально подвержены и более традиционным атакам с использование поддельных документов - паспортов, доверенностей," +
                        " платежных поручений и так далее, отметил эксперт. Разные формы хранения" +
                        " денежных средств требуют от банков разных мер защиты и нет объективных критериев, " +
                        "которые могли бы позволить сравнить их защищенность, добавил он."));
        //   searchingMap.put(ARTICLECOUNT, articles.getFirst());
        articles.addFirst(new Article(
                ArticleGroup.SPORTS ,"Сборная России завоевала бронзу в эстафете 4х50 м " +
                "комплексом на чемпионате Европы по плаванию на короткой воде",
                "Мужская сборная России завоевала бронзу в эстафете 4х50 метров комплексом на чемпионате Европы по плаванию на короткой воде.\n" +
                        "\n" +
                        "Российский квартет в составе Климента Колесникова, Олега Костина, Арину Сурковой и Марии Каменевой показали результат 1 минута и 36,42 секунды.\n" +
                        "\n" +
                        "Победу с мировым рекордом одержала сборная Нидерландов."));
        //     searchingMap.put(ARTICLECOUNT, articles.getFirst());
        articles.addFirst(new Article(
                ArticleGroup.TRAVELING ,"Самая молодая летчица в мире в ходе кругосветки прибыла в Магадан",
                "Летчица из Бельгии 19-летняя Зара Резефорд прилетела в Магадан. " +
                        "Она намерена стать самой молодой женщиной в истории, которая в одиночку облетела весь мир на небольшом воздушном судне, " +
                        "о чем стало известно в среду, 3 ноября.\n" +
                        "\n" +
                        "Предыдущий рекорд принадлежит 30-летней американке афганского происхождения Шаесте Вайз."));
        //    searchingMap.put(ARTICLECOUNT, articles.getFirst());
    }

    public Deque<Article> index(ArticleGroup selectedArticleGroup) {

        if (selectedArticleGroup == null) {
            return articles;
        } else {
            Deque<Article> selectedArticles = new ArrayDeque<>();
            for (Article article : articles) {

                if (article.getArticleGroup().getArticleGroupInRus().equals(selectedArticleGroup.getArticleGroupInRus())) {
                    selectedArticles.add(article);
                }
            }
            return selectedArticles;
        }
    }

    public void save(Article article) {
        articles.addFirst(article);
    }

    public void create(String header, String groupString, String text) {
        Article newArticle = new Article();
        newArticle.setArticleHeader(header);
        ArticleGroup articleGroup = Article.transformStringGroupToEnumArticleGroup(groupString);
        newArticle.setArticleGroup(articleGroup);
        newArticle.setArticleText(text);
        articles.addFirst(newArticle);
        //    searchingMap.put(ARTICLECOUNT, newArticle);
    }
/*
    public Article findArticleById(int articleID) {
        return articles.stream()
                .filter(article -> article.getArticleId() == articleID)
                .findAny()
                .orElse(null);
    }


    public void update(int articleId, Article updatedArticle) {
        Article articleToUpdate = findArticleById(articleId);
        articleToUpdate.setArticleGroup(updatedArticle.getArticleGroup());
        articleToUpdate.setArticleHeader(updatedArticle.getArticleHeader());
        articleToUpdate.setArticleText(updatedArticle.getArticleText());
    }

    public void delete(int articleID) {

        //   searchingMap.remove(articleID);
        articles.removeIf(article -> article.getArticleId() == articleID);

    }

 */
}
