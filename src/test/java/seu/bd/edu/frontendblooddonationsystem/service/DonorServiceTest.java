package seu.bd.edu.frontendblooddonationsystem.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seu.bd.edu.frontendblooddonationsystem.model.Donor;
import java.time.LocalDate;


@SpringBootTest
public class DonorServiceTest {
    @Autowired
    private DonorService donorService;

    @Test
    public void testSaveDonor() throws Exception {

        Donor donor = new Donor(105, "sonek pk", "A+",
                "pabna", "01758908899",
                "2016100000142@seu.edu.bd", LocalDate.now());

        Donor saveDonor = donorService.saveDonor(donor);
        Assertions.assertEquals(donor.getName(), saveDonor.getName());
    }
}
