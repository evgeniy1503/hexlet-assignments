package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
                .id.equalTo(id)
                .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        Validator<String> validatorFirstName = ctx.formParamAsClass("firstName", String.class)
                .check(firstNameVal -> !firstNameVal.isEmpty(), "Имя не может быть пустым");

        Validator<String> validatorLastName = ctx.formParamAsClass("lastName", String.class)
                .check(lastNameVal -> !lastNameVal.isEmpty(), "Фамилия не может быть пустой");


        Validator<String> validatorEmail = ctx.formParamAsClass("email", String.class)
               .check(emailVal -> EmailValidator.getInstance().isValid(email), "Не корректный Емаил");

        Validator<String> validatorPassword = ctx.formParamAsClass("password", String.class)
                .check(StringUtils::isNumeric, "Пароль должен содержать только цифры")
                .check(passwordValid -> passwordValid.length() > 4, "Пароль должен быть длинее 4 цифр");

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                validatorFirstName,
                validatorLastName,
                validatorEmail,
                validatorPassword
        );

        if (!errors.isEmpty()) {

            ctx.status(422);
            ctx.attribute("errors", errors);
            User invalidUser = new User(firstName, lastName, email, password);
            ctx.attribute("user", invalidUser);
            ctx.render("users/new.html");
            return;
        }

        User user = new User(firstName, lastName, email, password);
        user.save();
        ctx.attribute("flash", "Новый пользователь создан");
        ctx.redirect("/users");
        // END
    };
}
