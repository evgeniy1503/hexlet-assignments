package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Daytime now;

    @Autowired
    Meal meal;

    @GetMapping("/daytime")
    public String getWish() {
        return "It is " + now.getName() + " now. Enjoy your " + meal.getMealForDaytime(now.getName());
    }
}
// END
