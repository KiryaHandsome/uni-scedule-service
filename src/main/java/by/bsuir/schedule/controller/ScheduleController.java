package by.bsuir.schedule.controller;

import by.bsuir.schedule.model.Schedule;
import by.bsuir.schedule.service.ScheduleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleDao scheduleDao;

    @GetMapping("/groups/{groupNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule getScheduleByGroupNumber(@PathVariable Integer groupNumber) {
        return scheduleDao.findScheduleByGroupNumber(groupNumber);
    }

    @GetMapping("/teachers/{teacherName}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule getScheduleByGroupNumber(@PathVariable String teacherName) {
        return scheduleDao.findScheduleByTeacherName(teacherName);
    }
}
