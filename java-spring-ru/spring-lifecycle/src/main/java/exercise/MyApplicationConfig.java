package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        LocalDateTime now = LocalDateTime.now();
        int hourNow = now.getHour();
        if (hourNow >= 6 && hourNow < 12) {
            return new Morning();
        } else if (hourNow >= 12 && hourNow < 18) {
            return new Day();
        } else if (hourNow >= 18 && hourNow < 23) {
            return new Evening();
        } else {
            return new Night();
        }
    }
}
// END
