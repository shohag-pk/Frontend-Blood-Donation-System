package seu.bd.edu.frontendblooddonationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donor {
    private long id;
    private String name;
    private String blood_group;
    private String address;
    private String phone_number;
    private String email;
    private LocalDate dateOfBirth;
}
