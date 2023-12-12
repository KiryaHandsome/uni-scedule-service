package by.bsuir.schedule.controller;

import by.bsuir.schedule.dto.TeacherRequest;
import by.bsuir.schedule.model.Teacher;
import by.bsuir.schedule.service.TeacherDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherDao teacherDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@RequestBody TeacherRequest request) {
        teacherDao.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Teacher findTeacherById(@PathVariable Integer id) {
        return teacherDao.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Integer id) {
        teacherDao.delete(id);
    }
}
