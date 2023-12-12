package by.bsuir.schedule.controller;

import by.bsuir.schedule.dto.ClassroomRequest;
import by.bsuir.schedule.model.Classroom;
import by.bsuir.schedule.service.ClassroomDao;
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
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomDao classroomDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClassroom(@RequestBody ClassroomRequest request) {
        classroomDao.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Classroom findClassroomById(@PathVariable Integer id) {
        return classroomDao.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassroom(@PathVariable Integer id) {
        classroomDao.delete(id);
    }
}
