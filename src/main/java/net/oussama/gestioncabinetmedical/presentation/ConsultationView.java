package net.oussama.gestioncabinetmedical.presentation;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.oussama.gestioncabinetmedical.entities.Consultation;
import net.oussama.gestioncabinetmedical.entities.Patient;

import java.time.LocalDate;

public class ConsultationView extends VBox {

    private TextField idField;
    private DatePicker datePicker;
    private TextArea descriptionArea;
    private ComboBox<Patient> patientComboBox;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private TableView<Consultation> tableView;

    public ConsultationView() {
        // Link the external CSS stylesheet for ConsultationView
        this.getStylesheets().add(getClass().getResource("/styles/consultation-view.css").toExternalForm());


        setPadding(new Insets(20)); // Increased padding for a spacious view
        setSpacing(20);  // Increased spacing between components

        // Formulaire pour les informations de consultation
        GridPane formPane = new GridPane();
        formPane.setHgap(15); // Adjusted gap between elements
        formPane.setVgap(15); // Adjusted gap between elements
        formPane.setPadding(new Insets(20));
        formPane.getStyleClass().add("panel-primary");

        Label formTitle = new Label("Informations de Consultation");
        formTitle.getStyleClass().addAll("panel-heading", "h4");

        idField = new TextField();
        idField.setEditable(false);
        idField.setPromptText("ID (généré automatiquement)");

        datePicker = new DatePicker(LocalDate.now());

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description de la consultation");
        descriptionArea.setPrefRowCount(4);

        patientComboBox = new ComboBox<>();
        patientComboBox.setPromptText("Sélectionner un patient");

        formPane.add(new Label("ID:"), 0, 0);
        formPane.add(idField, 1, 0);
        formPane.add(new Label("Date:"), 0, 1);
        formPane.add(datePicker, 1, 1);
        formPane.add(new Label("Patient:"), 0, 2);
        formPane.add(patientComboBox, 1, 2);
        formPane.add(new Label("Description:"), 0, 3);
        formPane.add(descriptionArea, 1, 3);

        // Boutons d'action
        HBox buttonBar = new HBox(15);  // Increased spacing between buttons
        buttonBar.setPadding(new Insets(10, 0, 10, 0)); // Padding for button row

        addButton = new Button("Ajouter");
        addButton.getStyleClass().addAll("btn", "btn-success");

        updateButton = new Button("Modifier");
        updateButton.getStyleClass().addAll("btn", "btn-primary");

        deleteButton = new Button("Supprimer");
        deleteButton.getStyleClass().addAll("btn", "btn-danger");

        buttonBar.getChildren().addAll(addButton, updateButton, deleteButton);

        // Table des consultations
        tableView = new TableView<>();

        TableColumn<Consultation, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Consultation, java.sql.Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));

        TableColumn<Consultation, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Consultation, Patient> patientColumn = new TableColumn<>("Patient");
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        patientColumn.setCellFactory(column -> new TableCell<Consultation, Patient>() {
            @Override
            protected void updateItem(Patient patient, boolean empty) {
                super.updateItem(patient, empty);
                if (empty || patient == null) {
                    setText(null);
                } else {
                    setText(patient.getNom() + " " + patient.getPrenom());
                }
            }
        });

        tableView.getColumns().addAll(idColumn, dateColumn, descriptionColumn, patientColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Ajout des éléments à la vue
        getChildren().addAll(formTitle, formPane, buttonBar, tableView);

        // Sélection d'une ligne dans la table
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idField.setText(String.valueOf(newSelection.getId()));
                datePicker.setValue(newSelection.getDateConsultation().toLocalDate());
                descriptionArea.setText(newSelection.getDescription());
                patientComboBox.setValue(newSelection.getPatient());
            }
        });
    }

    public TextField getIdField() {
        return idField;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public ComboBox<Patient> getPatientComboBox() {
        return patientComboBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public TableView<Consultation> getTableView() {
        return tableView;
    }

    public void clearFields() {
        idField.clear();
        datePicker.setValue(LocalDate.now());
        descriptionArea.clear();
        patientComboBox.getSelectionModel().clearSelection();
    }

    public void setPatients(java.util.List<Patient> patients) {
        patientComboBox.setItems(FXCollections.observableArrayList(patients));
    }
}
