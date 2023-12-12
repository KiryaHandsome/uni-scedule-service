package by.bsuir.schedule.controller;

import by.bsuir.schedule.dto.SubjectRequest;
import by.bsuir.schedule.model.Subject;
import by.bsuir.schedule.service.SubjectDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectDao subjectDao;

    @PostMapping
    public void createSubject(@RequestBody SubjectRequest request) {
        subjectDao.create(request);
    }

    @GetMapping("/{id}")
    public Subject findSubjectById(@PathVariable Integer id) {
        return subjectDao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Integer id) {
        subjectDao.delete(id);
    }
}