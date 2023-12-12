package by.bsuir.schedule.controller;

import by.bsuir.schedule.dto.ClassRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Class;
import by.bsuir.schedule.service.ClassDao;
import by.bsuir.schedule.service.UserDao;
import by.bsuir.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassDao classDao;
    private final UserDao userDao;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClass(@RequestBody ClassRequest request) {
        classDao.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClass(@AuthenticationPrincipal UserDetails userDetails,
                            @PathVariable Integer id,
                            @RequestBody ClassRequest request) {
        String username = userDetails.getUsername();
        Class updateableClass = classDao.findById(id);
        if(!Objects.equals(updateableClass.getTeacher().getUserId(), userDao.findByEmail(username).getId())) {
            throw new RestException("Teacher hasn't access to edit this class", HttpStatus.FORBIDDEN);
        }
        classDao.update(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Class findClassById(@PathVariable Integer id) {
        return classDao.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Integer id) {
        classDao.delete(id);
    }
}
