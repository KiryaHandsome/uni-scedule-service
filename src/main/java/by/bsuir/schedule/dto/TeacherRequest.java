package by.bsuir.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequest {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String patronymic;
}
