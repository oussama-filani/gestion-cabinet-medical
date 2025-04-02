package net.oussama.gestioncabinetmedical.presentation;

import javafx.collections.FXCollections;
import net.oussama.gestioncabinetmedical.entities.Patient;
import net.oussama.gestioncabinetmedical.service.ICabinetService;

public class PatientController {

    private PatientView view;
    private ICabinetService service;

    public PatientController(PatientView view, ICabinetService service) {
        this.view = view;
        this.service = service;

        initListeners();
        loadPatients();
    }

    private void initListeners() {
        view.getAddButton().setOnAction(e -> addPatient());
        view.getUpdateButton().setOnAction(e -> updatePatient());
        view.getDeleteButton().setOnAction(e -> deletePatient());
    }

    private void loadPatients() {
        view.getTableView().setItems(FXCollections.observableArrayList(service.getAllPatients()));
    }

    private void addPatient() {
        String nom = view.getNomField().getText();
        String prenom = view.getPrenomField().getText();
        String tel = view.getTelField().getText();

        if (nom.isEmpty() || prenom.isEmpty()) {
            showAlert("Veuillez remplir au moins les champs Nom et Prénom");
            return;
        }

        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setTel(tel);

        service.addPatient(patient);
        view.clearFields();
        loadPatients();
    }

    private void updatePatient() {
        Patient selectedPatient = view.getTableView().getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert("Veuillez sélectionner un patient à modifier");
            return;
        }

        String nom = view.getNomField().getText();
        String prenom = view.getPrenomField().getText();
        String tel = view.getTelField().getText();

        if (nom.isEmpty() || prenom.isEmpty()) {
            showAlert("Veuillez remplir au moins les champs Nom et Prénom");
            return;
        }

        selectedPatient.setNom(nom);
        selectedPatient.setPrenom(prenom);
        selectedPatient.setTel(tel);

        service.updatePatient(selectedPatient);
        loadPatients();
    }

    private void deletePatient() {
        Patient selectedPatient = view.getTableView().getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert("Veuillez sélectionner un patient à supprimer");
            return;
        }

        service.deletePatient(selectedPatient);
        view.clearFields();
        loadPatients();
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}