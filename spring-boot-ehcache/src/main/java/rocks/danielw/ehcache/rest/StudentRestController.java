package rocks.danielw.ehcache.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rocks.danielw.ehcache.model.Student;
import rocks.danielw.ehcache.service.StudentService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/rest/students")
public class StudentRestController {

  private final StudentService studentService;

  @Autowired
  public StudentRestController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  public String get(@PathVariable Long id) {
    Optional<Student> student = studentService.find(id);

    if (student.isPresent()) {
      return student.get().toString();
    }

    return "";
  }

  @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String courseOfStudies) {
    Student newStudent = studentService.create(firstName, lastName, courseOfStudies);

    return newStudent.toString();
  }

}
