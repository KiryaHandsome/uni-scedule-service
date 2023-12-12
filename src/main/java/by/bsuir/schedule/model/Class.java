package by.bsuir.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class {

    private Integer id;
    private Subject subject;
    private Time startTime;
    private Time endTime;
    private Teacher teacher;
    private String comment;
    private ClassType classType;
    private Classroom classroom;
}
