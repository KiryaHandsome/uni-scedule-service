package by.bsuir.schedule.controller;

import by.bsuir.schedule.dto.FacultyRequest;
import by.bsuir.schedule.model.Faculty;
import by.bsuir.schedule.service.FacultyDao;
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
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyDao facultyDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFaculty(@RequestBody FacultyRequest request) {
        facultyDao.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Faculty findFacultyById(@PathVariable Integer id) {
        return facultyDao.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFaculty(@PathVariable Integer id) {
        facultyDao.delete(id);
    }
}