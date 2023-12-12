package by.bsuir.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private int groupNumber;
    private Date startDate;
    private Date endDate;
    private Faculty faculty;
    private List<Class> classes;
}
