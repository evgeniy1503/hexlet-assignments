package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUserByFullName(@RequestParam (required = false) String firstName, @RequestParam (required = false) String lastName) {

        if (lastName == null && firstName == null) {
            return userRepository.findAll();
        }

        String first = firstName == null ? "" : firstName;
        String last = lastName == null ? "" : lastName;

        return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(first)
                .and(QUser.user.lastName.containsIgnoreCase(last)));

    }
    // END
}

