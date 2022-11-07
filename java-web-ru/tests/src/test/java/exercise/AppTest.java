package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUser() {
        String firsName = "Evgeniy";
        String lastName = "Prokhorov";
        String email = "Evgeny15031991@mail.ru";
        String password = "12345";
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users/")
                .field("firstName", firsName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(302);
        User user = DB.find(User.class).where().eq("lastName", lastName)
                .findOne();
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(firsName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void testCreateInncorrectUser() {
        String firsName = "";
        String lastName = "Prokhorov";
        String email = "Evgeny15031991@mail.ru";
        String password = "12345";
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users/")
                .field("firstName", firsName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User user = DB.find(User.class).where().eq("lastName", lastName)
                .findOne();
        assertThat(user).isNull();
        String body = response.getBody();
        assertThat(body).contains(lastName);
        assertThat(body).contains(email);
        assertThat(body).contains("Имя не должно быть пустым");
    }
    // END
}
