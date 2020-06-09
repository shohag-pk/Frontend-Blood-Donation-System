package seu.bd.edu.frontendblooddonationsystem.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import seu.bd.edu.frontendblooddonationsystem.model.Donor;
import seu.bd.edu.frontendblooddonationsystem.service.DonorService;

import java.time.LocalDate;
@Route("")
public class MainView extends VerticalLayout {
    private DonorService donorService;

    public MainView(DonorService donorService) {
        super();
        this.donorService = donorService;
        add(getDonorForm());
        add(getDonorGrid());
    }

    private Grid<Donor> getDonorGrid() {
        Grid<Donor> donorGrid = new Grid<>(Donor.class);
        donorGrid.setItems(donorService.getDonors());
        donorGrid.setColumns("id","name","blood_group","address","phone_number","email","dateOfBirth");
        return  donorGrid;
    }


    private FormLayout getDonorForm() {

        FormLayout donorForm = new FormLayout();
        TextField idField = new TextField("ID");
        TextField nameField = new TextField("Name");
        TextField blood_groupField = new TextField("Blood_Group");
        TextField addressField = new TextField("Address");
        TextField phone_numberField = new TextField("Phone_Number");
        TextField emailField = new TextField("Email");
        DatePicker dateOfBirthPicker = new DatePicker("Date of Birth");
        Button createButton = new Button("create");
        createButton.addClickListener(event -> {
            long id = Long.parseLong(idField.getValue());
            String name = nameField.getValue();
            String blood_group = blood_groupField.getValue();
            String address = addressField.getValue();
            String phone_number =phone_numberField.getValue();
            String email = emailField.getValue();
            LocalDate dateOfBirth = dateOfBirthPicker.getValue();

            Donor donor = new Donor(id,name,blood_group,address,phone_number,email,dateOfBirth);
            saveDonor(donor);
        });
        donorForm.add(idField,nameField,blood_groupField,addressField,phone_numberField,
                phone_numberField,emailField,dateOfBirthPicker,createButton);

        return donorForm;

    }

    private void saveDonor(Donor donor) {

        try {
            Donor saveDonor = donorService.saveDonor(donor);
            Notification.show("saved" + saveDonor.getName());
        } catch (Exception e) {
            Notification.show("Coudln't save " + donor.getName());
        }

    }
}
