package by.bsuir.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {

    private Integer classroomId;
    private Integer teacherId;
    private Integer subjectId;
    private Integer typeId;
    private Time startTime;
    private Time endTime;
    private String comment;
    private List<Integer> weekNumbers;
    private Integer dayOfWeek;
}
