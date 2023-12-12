package by.bsuir.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String patronymic;
}
