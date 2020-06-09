package seu.bd.edu.frontendblooddonationsystem.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import seu.bd.edu.frontendblooddonationsystem.model.Donor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DonorService {
    private static final String BASE_URL = "http://localhost:8081/donors";
    private RestTemplate restTemplate;

    public DonorService() {
        restTemplate = new RestTemplate();
    }

    public List<Donor> getDonors() {
        ResponseEntity<Donor[]> donorsEntity = restTemplate.getForEntity(BASE_URL, Donor[].class);
        HttpStatus statusCode = donorsEntity.getStatusCode();
        Donor[] donors = donorsEntity.getBody();
        System.out.println(statusCode);
        return new ArrayList<>(Arrays.asList(donors));
    }

    public Donor saveDonor(Donor donor) throws Exception{
        System.out.println(donor);

        ResponseEntity<Donor> donorResponseEntity
                = restTemplate.postForEntity(BASE_URL, donor, Donor.class);
        HttpStatus statusCode = donorResponseEntity.getStatusCode();
        Donor saveDonor = donorResponseEntity.getBody();
        System.out.println("Status code:" + statusCode);
        System.out.println(saveDonor);
        if (statusCode.equals(HttpStatus.CREATED))
            throw new Exception("couldn't create donor " + donor);

        return saveDonor;
    }
}
