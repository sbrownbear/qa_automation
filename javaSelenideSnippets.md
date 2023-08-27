# Базовые команды Selenide Java

## Первая часть. Команды браузера.

Command|Discrabsion
---|---
open("https://google.com"); | Открыть сайт. Абсолютный путь (URL)
open("/customer/orders"); | Открыть сайт. Относительный путь (URL) + базовый URL
open("/", AuthenticationType.BASIC, new BasicAuthCredentials("", "user", "password")); | Открыть сайт с выпадающим окном (логин, пароль)
Selenide.back(); | Стрелка назад
Selenide.refresh(); | Обновить страницу
Selenide.clearBrowserCookies(); | Очистка файлов куки
Selenide.clearBrowserLocalStorage(); | Очистка Local Storage
executeJavaScript("sessionStorage.clear();"); | Очистка Session Storage
Selenide.confirm(); | Всегда соглашаемся, жмем «ОК». Выпадающее окно: OK (alert)
Selenide.dismiss(); | Всегда не соглашаемся, жмем «Отмена». Выпадающее окно: cancel, OK (alert)
Selenide.closeWindow(); | Закрыть текущее окно
Selenide.closeWebDriver(); | Закрыть браузер целиком
Selenide.switchTo().frame("new"); | Что-то нужно найти внутри фрейма
Selenide.switchTo().defaultContent(); | Выйти из этого режима в дом
Selenide.switchTo().window("The Internet"); | Перемещаться по окнам в браузере
var cookie = new Cookie("foo", "bar"); |
WebDriverRunner.getWebDriver().manage().addCookie(cookie); | Установка кук

## Вторая часть. Примеры селекторов. Selectors examples.

Command|Discrabsion
---|---
$("div").click(); | Первый вариант
element("div").click(); | Второй вариант (разницы нет)
$("div", 2).click(); |  Найти третий элемент (div) по индексу. Нумерация начинается с нуля
$x("//h1/div").click(); | Поиск элемента по икспасу. Вариант первый
$(byXpath("//h1/div")).click(); | Поиск элемента по икспасу. Вариант второй
$(byText("full text")).click(); | Поиск элемента по тексту. Полностью
$(withText("ull tex")).click(); | Поиск элемента по тексту. Частично
$(byTagAndText("div", "full text")); | Поиск элемента по тэгу и тексту одновременно. Полностью
$(withTagAndText("div", "ull text")); | Поиск элемента по тэгу и тексту одновременно. Частично
$("").parent(); | Поиск по DOM. Поиск по родителю
$("").sibling(1); | Поиск по дочерним элементам (сверху вниз)
$("").preceding(1); | Поиск по дочерним элементам (снизу вверх)
$("").closest("div"); | Поиск ближайщего. Ищет предков элемента снизу вверх
$("").ancestor("div"); | Поиск ближайщего (closest)
$("div:last-child"); | Псевдо селекторы
$("div").$("h1").find(byText("abc")).click(); | Поиск div, внутри div ищем h1, внутри h1 ищем текст "abc" find = $. Find работает только со второго поиска по элементу
$(byAttribute("abc", "x")).click(); | Поиск по атрибуту – квадратные скобки. Вариант первый.
$("[abc=x]").click(); | Поиск по атрибуту. Вариант второй
$(byId("mytext")).click(); | Поиск по ID. Вариант первый
$("#mytext").click(); | Поиск по ID. Вариант второй
$(byClassName("red")).click(); | Поиск по css селектору. Вариант первый
$(".red").click(); | Поиск по css селектору. Вариант второй

## Третья часть. Действия. Actions examples.

Command|Discrabsion
---|---
$("").click(); | Мышка. Клик по элементу
$("").doubleClick(); | Мышка. Двойной клик по элементу
$("").contextClick(); | Мышка. Правый клик (старые мышки)
$("").hover(); | Мышка. Подвести курсор и не кликать
$("").setValue("text"); | Очистить поле и поместить значение
$("").append("text"); | Не очищать поле и поместить значение. Добавит текст к существующему
$("").clear(); | Очистить поле (не всегда срабатывает). Есть фреймворки, где команда не работает
$("").setValue(""); | Очистить поле путем помещения в поле пустой строки. Аналог clear (говорят что нет разницы)
$("div").sendKeys("c"); | Клавиши. Нажать клавишу на конкретном элементе.
actions().sendKeys("c").perform(); | Клавиши. Нажать клавишу во всем приложении, без каких-либо привязок к элементу.
actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); | Клавиши. Комбинация клавиш (Ctrl + F)
$("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); | Клавиши. Комбинация клавиш (Ctrl + F). Пример применения клавиши по тегу html (вся страница)
$("").pressEnter(); | Клавиши. Нажать Enter
$("").pressEscape(); | Клавиши. Нажать Escape
$("").pressTab(); | Клавиши. Нажать Tab
actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform(); | Комбинация клавиши и мышки. Кликнуть на левую кнопку мышки и не отпускать
$("").selectOption("dropdown_option"); | Дропдауны (старые)
$("").selectRadio("radio_options"); | Радиобокс

## Четвертая часть. Assertions (проверки). По умолчанию тайм-ауты 4 секунды (можно менять).

Command|Discrabsion
---|---
$("").shouldBe(visible); | Элемент должен быть видимым. Вариант первый.
$("").shouldNotBe(visible); | Элемент не должен быть видимым. Вариант первый.
$("").shouldHave(text("abc")); | Элемент должен быть видимым. Вариант второй.
$("").shouldNotHave(text("abc")); | Элемент не должен быть видимым. Вариант второй.
$("").should(appear); | Элемент должен быть видимым. Вариант третий.
$("").shouldNot(appear); | Элемент не должен быть видимым. Вариант третий.
$("").shouldBe(visible, Duration.ofSeconds(30)); | Элемент должен быть видимым в течении 30 секунд. Кастомная настройка таймаута.

## Пятая часть. Условия. Conditions. Внутри каждого assert должно быть условия.
80% всех проверок это $("").shouldBe(visible); или $("").shouldHave(text("abc"));

Command|Discrabsion
---|---
$("").shouldBe(visible); | Элемент должен быть видимым
$("").shouldBe(hidden); | Элемент должен быть скрыт
$("").shouldHave(text("abc")); | Поиск текста частично. Без регистра
$("").shouldHave(exactText("abc")); | Поиск текста полностью. Без регистра
$("").shouldHave(textCaseSensitive("abc")); | Поиск текста частично. С учетом регистра
$("").shouldHave(exactTextCaseSensitive("abc")); | Поиск текста полностью. С учетом регистра
$("").should(matchText("[0-9]abc$")); | Сложная проверка текста. Например проверка почты. Используется редко
$("").shouldHave(cssClass("red")); | Проверка по классу
$("").shouldHave(cssValue("font-size", "12")); | Проверка элемента. Настоящее свойство элемента (Elements, Computed)
$("").shouldHave(value("25")); | Проверка текста. Ввели в поисковую строку тектст и проверили что оно совпадает (не полностью)
$("").shouldHave(exactValue("25")); | роверка текста. Ввели в поисковую строку тектст и проверили что оно совпадает (полностью)
$("").shouldBe(empty); | Поле пустое
$("").shouldHave(attribute("disabled")); | Атрибуты. Поиск элемента по атрибутам
$("").shouldHave(attribute("name", "example")); | Атрибуты. Поиск элемента по значению
$("").shouldHave(attributeMatching("name", "[0-9]abc$")); | Атрибуты. Проверка атрибута сложно. Используется редко
$("").shouldBe(checked); | Чекбокс, стоит галочка. Checkbox
$("").shouldNotBe(checked); | Чекбокс, не стоит галочка. Checkbox
$("").should(exist); | Проверка нахождения элемента в DOM, при этом пользователь может его не видеть
$("").shouldBe(disabled); | Поиск осуществляется по атрибуту. Может не работать. Используется редко. Кнопка (button) не кликабельна
$("").shouldBe(enabled); | Поиск осуществляется по атрибуту. Может не работать. Используется редко. Кнопка (button) кликабельна

## Шестая часть. Коллекции (collections)

Command|Discrabsion
---|---
$$("div"); | Поиск по селектору. Ничего не делает. Должно быть действие, например: $$("div").first().click();
$$x("//div"); | Поиск по икспасу.
$$("div").filterBy(text("123")).shouldHave(size(1)); | Фильтрация. Selections. Поиск удовлетворяющий условия (filterBy).
$$("div").excludeWith(text("123")).shouldHave(size(1)); | Фильтрация. Selections. Поиск не удовлетворяющий условия (excludeWith). Выбрасывает все элементы "123".
$$("div").first().click(); | Навигация. Сводит из коллекции в один элемент. Поиск первого элемента. Вариант первый.
elements("div").first().click(); | Навигация. Вариант второй.
$$("div").last().click(); | Навигация. Сводит из коллекции в один элемент. Поиск последнего элемента.
$$("div").get(1).click(); | Навигация. Поиск второго элемента. Начинается с 0. Вариант первый.
$("div", 1).click(); | Навигация. Вариант второй.
$$("div").findBy(text("123")).click(); | Навигация. Ищет первый элемент. Комбинация filterBy и first.

## Седьмая часть. Assertions

Command|Discrabsion
---|---
$$("").shouldHave(size(0)); | Проверка размера. В данном примере коллекция пустая.
$$("").shouldBe(CollectionCondition.empty); | Проверка размера. То же, что выше.
$$("").shouldHave(texts("Alfa", "Beta", "Gamma")); | Проверка, что коллекция содержит данный текст. Частично. Точное кол-ва элементов. "Alfa1", "Beta1", "Gamma5" - тест пройдет.
$$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); | Проверка, что коллекция содержит данный текст. Полностью. "Alfa1", "Beta1", "Gamma5" - тест не пройдет.
$$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); | Проверка, что коллекция содержит данный текст, без учета порядка.
$$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa")); | С учетом регистра.
$$("").shouldHave(itemWithText("Gamma")); | Поиск конкретного элемента по тексту. Коллекция содержит один элемент, не важно в каком месте.
$$("").shouldHave(sizeGreaterThan(0)); | Размер коллекции больше 0.
$$("").shouldHave(sizeGreaterThanOrEqual(1)); | Размер коллекции больше 1.
$$("").shouldHave(sizeLessThan(3)); | Размер коллекции меньше 3.
$$("").shouldHave(sizeLessThanOrEqual(2)); | Размер коллекции меньше 2.

## Восьмая часть. Операции с файлами. File Operation

Command|Discrabsion
---|---
File file1 = $("a.fileLink").download(); | Скачать файл. Работает только с <a href="..">
File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); | Скачать файл. Работает всегда. Чаще используют. Возможно проблемы с Grid/Selenoid.
File file = new File("src/test/resources/readme.txt"); | Загрузить файл. Файл обычно помещаем в папку resources. Вариант первый.
$("#file-upload").uploadFile(file); | Вариант первый.
$("#file-upload").uploadFromClasspath("readme.txt"); | Вариант второй.
$("uploadButton").click(); | После, необходимо загрузить файл. Кликнуть на кнопку загрузить.

## Девятая часть. JavaSctipt примеры

Command|Discrabsion
---|---
executeJavaScript("alert('selenide')"); | Запуск
executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12); | Запуск с аргументами
long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7); | Запуск с аргументами и возвращением результата

