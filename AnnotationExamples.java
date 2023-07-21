import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnnotationExamples {
    // Жизненный цикл теста (предусловия)
    // Этот метод будет запускаться перед всеми тестами (настройка окружения для всех тестов в этом классе)
    @BeforeAll
    static void beforeAll() {
        // Some code
    }
    // Метод запускатся после всех тестов (что-то почистить за собой)
    @AfterAll
    static void afterAll() {
        // Some code
    }
    // Метод запускатся перед каждым тестом (подготовить тест к запуску)
    @BeforeEach
    void beforeEach() {
        // Some code
    }
    // Метод запускатся после каждого теста (что-то почистить за собой)
    @AfterEach
    void afterEach() {
        // Some code
    }

    // Пример аннотации. Добавили пользователя, открыли его страницу
    @BeforeEach
    void beforeEach() {
        Cookie cookie = apiClient.loginUser(User.ADMIN);
        Selenide.addCookie(cookie);
        Selenide.refresh();
        ProfilePage.openProfile();
    }

    @Disabled("") // Тест не воспроизодится. Причины: минорный баг (вставили id бага), функциональность немного изменилась/не актуальна
    @DisplayName("Адрес https://selenide.org должен быть в выдаче гугла по запросу 'Selenide'") // Аннотация, позволяющая комментировать тесты. Интегрировано с Allure
    @Test // Аннотация - метка теста (не влияет на работу теста)
    @Tag("BLOCKER") // or @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void successfulTest() { // Чтобы не давать названия методу selenideSitUrlShouldBePresentInResultsOfSearchInGoogleBySelenideQuery(), есть аннотация @DisplayName("")
        open("https://www.google.com/");
        $("").setValue("selenide").pressEnter();
        $("").shouldHave(text("https://selenide.org"));
    }
}
