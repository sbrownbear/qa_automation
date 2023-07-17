package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// This is not a full list, just the most common. https://github.com/qa-guru/knowledge-base/wiki/9.-Selenide-%231
public class Snippets {

  // Команды браузера
  void browser_command_examples() {
    // Открытие страницы:
    open("https://google.com"); // Открыть сайт. Абсолютный путь (URL).
    open("/customer/orders"); // Открыть сайт. Относительный путь (URL) + базовый URL. Configuration.baseUrl = "https://google.com".
    open("/", AuthenticationType.BASIC,
            new BasicAuthCredentials("", "user", "password")); // Открыть сайт с выпадающим окном (логин, пароль).

    Selenide.back(); // Стрелка назад.
    Selenide.refresh(); // Обновить страницу.

    // Зарегистрировались, снова хотим зайти. Гарантия, что пользователь не авторизован.
    Selenide.clearBrowserCookies(); // Очистка файлов куки.
    Selenide.clearBrowserLocalStorage(); // Очистка Local Storage.
    executeJavaScript("sessionStorage.clear();"); // Очистка Session Storage.
    // После обновить страницу командой Selenide.refresh();

    // Просмотр кук - DevTools, Application, Storage, Local Storage, Session Storage.

    // Браузерные всплывающие окна (alert):
    Selenide.confirm(); // Всегда соглашаемся, жмем «ОК». Выпадающее окно: OK.
    Selenide.dismiss(); // Всегда не соглашаемся, жмем «Отмена». Выпадающее окно: cancel, OK.

    Selenide.closeWindow(); // Закрыть текущее окно.
    Selenide.closeWebDriver(); // Закрыть браузер целиком.

    // Фрейм - страничка в страничке. Довольно медленно работает.
    Selenide.switchTo().frame("new"); // Что-то нужно найти внутри фрейма.
    Selenide.switchTo().defaultContent(); // Выйти из этого режима в дом.

    Selenide.switchTo().window("The Internet"); // Перемещаться по окнам в браузере.

    // Создаем куки. Какие ставить куки, помогут разработчики.
    // open("http://testserver/empty.txt") - сначало открываем любой файл на нашем сервере.
    var cookie = new Cookie("foo", "bar");
    WebDriverRunner.getWebDriver().manage().addCookie(cookie); // Установка кук.
    // После обновить страницу командой Selenide.refresh(); - опционально.
    // open("http://testserver/recommendation") - открывает то, что хотели проверить.

    // Selenide запускает браузер в режиме инкогнито по умолчанию.
  }

  // Селекторы
  void selectors_examples() {
    // Селекторы обозначаются с помощью символа доллара — $. Но в языке Kotlin этот символ
    // зарезервирован для внутреннего использования, поэтому вместо него следует использовать
    // ключевое слово element.

    // Поиск элемента по селектору.
    $("div").click(); // Первый вариант.
    element("div").click(); // Второй вариант (разницы нет).

    $("div", 2).click(); // Найти третий элемент (div) по индексу. Нумерация начинается с нуля.

    // Поиск элемента по икспасу.
    $x("//h1/div").click(); // Вариант первый.
    $(byXpath("//h1/div")).click(); // Вариант второй.
    // $(‘div’).$x(‘.//h1’); Поиск по селектору, после по икспасу.

    // Поиск элемента по тексту.
    $(byText("full text")).click(); // Полностью.
    $(withText("ull tex")).click(); // Частично.

    // Поиск элемента по тэгу и тексту одновременно.
    $(byTagAndText("div", "full text")); // Полностью.
    $(withTagAndText("div", "ull text")); // Частично.

    // Поиск по DOM. Selenide ищет всегда вниз по дереву.
    $("").parent(); // Поиск по родителю.
    $("").sibling(1); // Поиск по дочерним элементам (сверху вниз).
    $("").preceding(1); // Поиск по дочерним элементам (снизу вверх).
    $("").closest("div"); // Поиск ближайщего. Ищет предков элемента снизу вверх.
    $("").ancestor("div"); // Поиск ближайщего (closest).
    $("div:last-child"); // Псевдо селекторы.

    $("div").$("h1").find(byText("abc")).click(); // Поиск div, внутри div ищем h1, внутри
    // h1 ищем текст "abc" find = $. Find работает только со второго поиска по элементу.

    // Используется очень редко.
    // Поиск по атрибуту – квадратные скобки.
    $(byAttribute("abc", "x")).click(); // Вариант первый.
    $("[abc=x]").click(); // Вариант второй.

    // Поиск по ID – решетка
    $(byId("mytext")).click(); // Вариант первый.
    $("#mytext").click(); // Вариант второй.

    // Поиск по css селектору – точка
    $(byClassName("red")).click(); // Вариант первый.
    $(".red").click(); // Вариант второй.
  }

  // Команды, действия.
  void actions_examples() {
    // Мышка.
    $("").click(); // Клик по элементу.
    $("").doubleClick(); // Двойной клик по элементу.
    $("").contextClick(); // Правый клик (старые мышки).

    $("").hover(); // Подвести курсор и не кликать.

    // Текстовые поля.
    $("").setValue("text"); // Очистить поле и поместить значение.
    $("").append("text"); // Не очищать поле и поместить значение. Добавит текст к существующему.
    $("").clear(); // Очистить поле (не всегда срабатывает). Есть фреймворки, где команда не работает.
    $("").setValue(""); // Очистить поле путем помещения в поле пустой строки. Аналог clear (говорят что нет разницы).

    // Клавиши.
    $("div").sendKeys("c"); // Нажать клавишу на конкретном элементе.
    actions().sendKeys("c").perform(); // Нажать клавишу во всем приложении, без каких-либо привязок к элементу.
    actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Комбинация клавиш (Ctrl + F).
    $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // Комбинация клавиш (Ctrl + F). Пример применения клавиши по тегу html (вся страница)

    // Часто используют.
    $("").pressEnter(); // Нажать Enter.
    $("").pressEscape(); // Нажать Escape.
    $("").pressTab(); // Нажать Tab.

    // Сложные комбинации нажатия клавиши и мышки. Начинаются команды методом actions(), а заканчиваются perform().
    actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform(); // Комбинация клавиши и мышки.
    // Кликнуть на левую кнопку мышки и не отпускать.

    // Старые html экшены. С современными фреймворками может не работать.
    $("").selectOption("dropdown_option"); // Дропдауны (старые).
    $("").selectRadio("radio_options"); // Радиобокс.
  }

  // Assertions (проверки). По умолчанию тайм-ауты 4 секунды (можно менять).
  void assertions_examples() {
    $("").shouldBe(visible); // Элемент должен быть видимым. Вариант первый.
    $("").shouldNotBe(visible); // Элемент не должен быть видимым. Вариант первый.
    $("").shouldHave(text("abc")); // Элемент должен быть видимым. Вариант второй.
    $("").shouldNotHave(text("abc")); // Элемент не должен быть видимым. Вариант второй.
    $("").should(appear); // Элемент должен быть видимым. Вариант третий.
    $("").shouldNot(appear); // Элемент не должен быть видимым. Вариант третий.

    // Увеличить или уменьшить timeout (таймаут). Кастомная настройка таймаута.
    $("").shouldBe(visible, Duration.ofSeconds(30)); // Элемент должен быть видимым в течении 30 секунд.

  }

  // Условия проверок. Conditions. Внутри каждого assert должно быть условия.
  void conditions_examples() {
    // 80% всех проверок это $("").shouldBe(visible); или $("").shouldHave(text("abc"));
    $("").shouldBe(visible); // Элемент должен быть видимым.
    $("").shouldBe(hidden); // Элемент должен быть скрыт.

    // Условия содержания текста.
    // Поиск текста частично. Без регистра.
    $("").shouldHave(text("abc"));
    // Поиск текста полностью. Без регистра.
    $("").shouldHave(exactText("abc"));
    // Поиск текста частично. С учетом регистра.
    $("").shouldHave(textCaseSensitive("abc"));
    // Поиск текста полностью. С учетом регистра.
    $("").shouldHave(exactTextCaseSensitive("abc"));
    // Сложная проверка текста.
    $("").should(matchText("[0-9]abc$")); // Например проверка почты. Используется редко.


    // CSS
    // Проверка класса
    $("").shouldHave(cssClass("red")); // Проверка по классу

    // Проверка элемента
    $("").shouldHave(cssValue("font-size", "12")); // Настоящее свойство элемента (Elements, Computed).

    // Поля ввода
    $("").shouldHave(value("25")); // Проверка текста. Ввели в поисковую строку тектст и проверили что оно совпадает (не полностью).
    $("").shouldHave(exactValue("25")); // Проверка текста. Ввели в поисковую строку тектст и проверили что оно совпадает (полностью).
    $("").shouldBe(empty); // Поле пустое.

    // Атрибуты
    $("").shouldHave(attribute("disabled")); // Поиск элемента по атрибутам.
    $("").shouldHave(attribute("name", "example")); // Поиск элемента по значению.
    $("").shouldHave(attributeMatching("name", "[0-9]abc$")); // Проверка атрибута сложно. Используется редко.

    // Чекбоксы
    $("").shouldBe(checked); // Чекбокс, стоит галочка. Checkbox.
    $("").shouldNotBe(checked); // Чекбокс, не стоит галочка. Checkbox.

    // Проверка нахождения элемента в DOM, при этом пользователь может его не видеть.
    $("").should(exist); // Используется редко.

    // Поиск осуществляется по атрибуту. Может не работать. Используется редко.
    $("").shouldBe(disabled); // Кнопка (button) не кликабельна.
    $("").shouldBe(enabled); // Кнопка (button) кликабельна.
  }

  // Коллекции
  void collections_examples() {

    $$("div"); // Поиск по селектору. Ничего не делает. Должно быть действие, например: $$("div").first().click();
    $$x("//div"); // Поиск по икспасу.

    // Фильтрация. Selections.
    $$("div").filterBy(text("123")).shouldHave(size(1)); // Поиск удовлетворяющий условия (filterBy).
    $$("div").excludeWith(text("123")).shouldHave(size(1)); // Поиск не удовлетворяющий условия (excludeWith). Выбрасывает все элементы "123".

    // Навигация.
    $$("div").first().click(); // Сводит из коллекции в один элемент. Поиск первого элемента. Вариант первый.
    elements("div").first().click(); // Вариант второй.
    $$("div").last().click(); // Сводит из коллекции в один элемент. Поиск последнего элемента.
    $$("div").get(1).click(); // Поиск второго элемента. Начинается с 0. Вариант первый.
    $("div", 1).click(); // Вариант второй.
    $$("div").findBy(text("123")).click(); // Ищет первый элемент. Комбинация filterBy и first.

    // Assertions. Проверки коллекций.
    // Размер.
    $$("").shouldHave(size(0)); // Проверка размера. В данном примере коллекция пустая.
    $$("").shouldBe(CollectionCondition.empty); // Проверка размера. То же, что выше.

    // Проверка, что коллекция содержит данный текст. Частично. Точное кол-ва элементов. "Alfa1", "Beta1", "Gamma5" - тест пройдет.
    $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
    // Проверка, что коллекция содержит данный текст. Полностью. "Alfa1", "Beta1", "Gamma5" - тест не пройдет.
    $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

    // Проверка, что коллекция содержит данный текст, без учета порядка.
    $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
    // С учетом регистра.
    $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

    // Поиск конкретного элемента по тексту
    $$("").shouldHave(itemWithText("Gamma")); // Коллекция содержит один элемент, не важно в каком месте.

    // Проверка размера коллекции
    $$("").shouldHave(sizeGreaterThan(0)); // Размер коллекции больше 0.
    $$("").shouldHave(sizeGreaterThanOrEqual(1)); // Размер коллекции больше 1.
    $$("").shouldHave(sizeLessThan(3)); // Размер коллекции меньше 3.
    $$("").shouldHave(sizeLessThanOrEqual(2)); // Размер коллекции меньше 2.

  }

  // Операции с файлами (загрузки документов).
  void file_operation_examples() throws FileNotFoundException {

    // Скачать файл. Работает только с <a href="..">
    File file1 = $("a.fileLink").download();

    // Скачать файл. Работает всегда. Чаще используют. Возможно проблемы с Grid/Selenoid.
    File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));

    // Загрузить файл.
    File file = new File("src/test/resources/readme.txt"); // Файл обычно помещаем в папку resources. Вариант первый.
    $("#file-upload").uploadFile(file); // Вариант первый.
    $("#file-upload").uploadFromClasspath("readme.txt"); // Вариант второй.
    // После, необходимо загрузить файл.
    $("uploadButton").click(); // Кликнуть на кнопку загрузить.
  }

  void javascript_examples() {
    // Запуск
    executeJavaScript("alert('selenide')");
    // Запуск с аргументами
    executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
    // Запуск с аргументами и возвращением результата
    long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

  }
}

