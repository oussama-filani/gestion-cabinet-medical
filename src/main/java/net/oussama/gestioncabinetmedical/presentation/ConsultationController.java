package net.oussama.gestioncabinetmedical.presentation;

import javafx.collections.FXCollections;
import net.oussama.gestioncabinetmedical.entities.Consultation;
import net.oussama.gestioncabinetmedical.entities.Patient;
import net.oussama.gestioncabinetmedical.service.ICabinetService;

import java.sql.Date;
import java.time.LocalDate;

public class ConsultationController {

    private ConsultationView view;
    private ICabinetService service;

    public ConsultationController(ConsultationView view, ICabinetService service) {
        this.view = view;
        this.service = service;

        initListeners();
        loadConsultations();
        loadPatients();
    }

    private void initListeners() {
        view.getAddButton().setOnAction(e -> addConsultation());
        view.getUpdateButton().setOnAction(e -> updateConsultation());
        view.getDeleteButton().setOnAction(e -> deleteConsultation());
    }

    private void loadConsultations() {
        view.getTableView().setItems(FXCollections.observableArrayList(service.getAllConsultations()));
    }

    private void loadPatients() {
        view.setPatients(service.getAllPatients());
    }

    private void addConsultation() {
        LocalDate localDate = view.getDatePicker().getValue();
        String description = view.getDescriptionArea().getText();
        Patient patient = view.getPatientComboBox().getValue();

        if (localDate == null || description.isEmpty() || patient == null) {
            showAlert("Veuillez remplir tous les champs");
            return;
        }

        Consultation consultation = new Consultation();
        consultation.setDateConsultation(Date.valueOf(localDate));
        consultation.setDescription(description);
        consultation.setPatient(patient);

        service.addConsultation(consultation);
        view.clearFields();
        loadConsultations();
    }

    private void updateConsultation() {
        Consultation selectedConsultation = view.getTableView().getSelectionModel().getSelectedItem();
        if (selectedConsultation == null) {
            showAlert("Veuillez sélectionner une consultation à modifier");
            return;
        }

        LocalDate localDate = view.getDatePicker().getValue();
        String description = view.getDescriptionArea().getText();
        Patient patient = view.getPatientComboBox().getValue();

        if (localDate == null || description.isEmpty() || patient == null) {
            showAlert("Veuillez remplir tous les champs");
            return;
        }

        selectedConsultation.setDateConsultation(Date.valueOf(localDate));
        selectedConsultation.setDescription(description);
        selectedConsultation.setPatient(patient);

        service.updateConsultation(selectedConsultation);
        loadConsultations();
    }

    private void deleteConsultation() {
        Consultation selectedConsultation = view.getTableView().getSelectionModel().getSelectedItem();
        if (selectedConsultation == null) {
            showAlert("Veuillez sélectionner une consultation à supprimer");
            return;
        }

        service.deleteConsultation(selectedConsultation);
        view.clearFields();
        loadConsultations();
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}