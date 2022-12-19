package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "{id}/previous")
    public List<Course> getPreviousCorses(@PathVariable("id") long id) {

        Course course = courseRepository.findById(id);
        String path = course.getPath();

        if (path == null) {
            return new ArrayList<>();
        }
        String[] allPreviouses = path.split("\\.");
        System.out.println(Arrays.toString(allPreviouses));

        List<Course> courseList = new ArrayList<>();

        for (String idPrev : allPreviouses) {
            long correctId = Long.parseLong(idPrev);
            courseList.add(courseRepository.findById(correctId));
        }

        return courseList;
    }
    // END

}
